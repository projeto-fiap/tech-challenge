package tech.fiap.project.infra.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.fiap.project.domain.entity.InstructionPaymentOrder;
import tech.fiap.project.domain.usecase.CreatePaymentUrl;
import tech.fiap.project.infra.configuration.MercadoPagoConstants;
import tech.fiap.project.infra.configuration.MercadoPagoProperties;
import tech.fiap.project.infra.dto.CashOutDTO;
import tech.fiap.project.infra.dto.ItemMercadoLivreDTO;
import tech.fiap.project.infra.dto.PaymentRequestDTO;
import tech.fiap.project.infra.dto.PaymentResponseDTO;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CreatePaymentUrlMercadoPagoService implements CreatePaymentUrl {

	private RestTemplate restTemplateMercadoPago;

	private MercadoPagoProperties mercadoPagoProperties;

	@Override
	public String execute(InstructionPaymentOrder instructionPaymentOrder) {
		String url = MercadoPagoConstants.BASE_URI + buildBaseUrl();
		HttpHeaders headers = getHttpHeaders();
		PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO(new CashOutDTO(0),
				instructionPaymentOrder.getDescription(), instructionPaymentOrder.getExternalReference(),
				buildItems(instructionPaymentOrder), instructionPaymentOrder.getNotificationUrl(),
				instructionPaymentOrder.getTitle(), instructionPaymentOrder.getAmount());
		RequestEntity<PaymentRequestDTO> body = RequestEntity.post(url).headers(headers).body(paymentRequestDTO);
		ResponseEntity<PaymentResponseDTO> exchange = restTemplateMercadoPago.exchange(body, PaymentResponseDTO.class);
		return Objects.requireNonNull(exchange.getBody()).getQrData();
	}

	private List<ItemMercadoLivreDTO> buildItems(InstructionPaymentOrder instructionPaymentOrder) {
		ItemMercadoLivreDTO item = new ItemMercadoLivreDTO("A123K9191938", "marketplace",
				instructionPaymentOrder.getTitle(), instructionPaymentOrder.getDescription(),
				instructionPaymentOrder.getAmount(), 1, "unit", instructionPaymentOrder.getAmount());
		return List.of(item);
	}

	private HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		String token = mercadoPagoProperties.getAccessToken();
		headers.add("Authorization", "Bearer  " + token);
		return headers;
	}

	private String buildBaseUrl() {
		String userId = mercadoPagoProperties.getUserId();
		String pos = mercadoPagoProperties.getPos();
		return String.format(MercadoPagoConstants.BASE_PAYMENT_METHOD, userId, pos);
	}

}
