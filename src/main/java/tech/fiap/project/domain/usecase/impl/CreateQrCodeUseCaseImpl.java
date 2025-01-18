package tech.fiap.project.domain.usecase.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.CreateQrCodeUseCase;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

public class CreateQrCodeUseCaseImpl implements CreateQrCodeUseCase {

	RestTemplate restTemplatePayments;
	RestTemplate restTemplateKeycloak;

	String clientId;
	String keycloakBaseUrl;
	String clientSecret;

	public CreateQrCodeUseCaseImpl(RestTemplate restTemplatePayments, RestTemplate restTemplateKeycloak, String keycloakBaseUrl,String clientId, String clientSecret) {
		this.restTemplatePayments = restTemplatePayments;
		this.restTemplateKeycloak = restTemplateKeycloak;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.keycloakBaseUrl = keycloakBaseUrl;
	}

	public BufferedImage execute(Order order) throws IOException {
		String accessToken = getAccessToken();
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);
		HttpEntity<Order> requestEntity = new HttpEntity<>(order, headers);
		ResponseEntity<byte[]> response = restTemplatePayments.exchange(
				"http://localhost:8081/api/v1/payments",
				HttpMethod.POST,
				requestEntity,
				byte[].class
		);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(Objects.requireNonNull(response.getBody()));
        return ImageIO.read(inputStream);
	}

	private String getAccessToken() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		String body = String.format("client_id=%s&client_secret=%s&grant_type=client_credentials", clientId, clientSecret);
		HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
		ResponseEntity<ObjectNode> response = restTemplateKeycloak.exchange(
				keycloakBaseUrl + "/realms/master/protocol/openid-connect/token",
				HttpMethod.POST,
				requestEntity,
				ObjectNode.class
		);
        return Objects.requireNonNull(response.getBody()).get("access_token").asText();
	}

}
