package tech.fiap.project.domain.usecase.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateQrCodeUseCaseImplTest {

    private RestTemplate restTemplatePayments = mock(RestTemplate.class);
    private RestTemplate restTemplateKeycloak = mock(RestTemplate.class);
    private CreateQrCodeUseCaseImpl useCase;

    private final String keycloakBaseUrl = "http://localhost:8080";
    private final String clientId = "test-client";
    private final String clientSecret = "test-secret";

    @BeforeEach
    void setUp() {
        useCase = new CreateQrCodeUseCaseImpl(restTemplatePayments, restTemplateKeycloak, keycloakBaseUrl, clientId, clientSecret);
    }

    @Test
    void testExecute_ShouldReturnBufferedImage() throws Exception {
        Order order = new Order(1L, OrderStatus.AWAITING_PAYMENT, LocalDateTime.MIN, LocalDateTime.MIN, null, null, null, BigDecimal.ZERO);

        when(restTemplateKeycloak.exchange(anyString(), eq(HttpMethod.POST), any(), eq(ObjectNode.class)))
                .thenReturn(createMockedTokenResponse());

        byte[] imageBytes = new byte[0];
        ResponseEntity<byte[]> mockedResponse = new ResponseEntity<>(imageBytes, HttpStatus.OK);
        when(restTemplatePayments.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(byte[].class)))
                .thenReturn(mockedResponse);

        BufferedImage result = useCase.execute(order);

        assertNull(result);
    }

    @Test
    void testExecute_ShouldThrowException_WhenResponseIsNull() {
        // Arrange
        Order order = new Order(1L, OrderStatus.AWAITING_PAYMENT, LocalDateTime.MIN, LocalDateTime.MIN, null, null, null, BigDecimal.ZERO);

        when(restTemplateKeycloak.exchange(anyString(), eq(HttpMethod.POST), any(), eq(ObjectNode.class)))
                .thenReturn(createMockedTokenResponse());

        when(restTemplatePayments.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(byte[].class)))
                .thenReturn(null);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> useCase.execute(order));
    }

    @Test
    void testGetAccessToken_ShouldReturnAccessToken() {
        // Arrange
        String accessToken = "mockedAccessToken";
        when(restTemplateKeycloak.exchange(anyString(), eq(HttpMethod.POST), any(), eq(ObjectNode.class)))
                .thenReturn(createMockedTokenResponse());

        // Act
        String result = useCase.getAccessToken();

        // Assert
        assertEquals(accessToken, result);
    }

    @Test
    void testGetAccessToken_ShouldThrowException_WhenTokenResponseIsNull() {
        // Arrange
        when(restTemplateKeycloak.exchange(anyString(), eq(HttpMethod.POST), any(), eq(ObjectNode.class)))
                .thenReturn(null);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> useCase.getAccessToken());
    }

    @SneakyThrows
    private ResponseEntity<ObjectNode> createMockedTokenResponse() {
        String fromValue = "{\"access_token\": \"mockedAccessToken\"}";
        ObjectNode mockedResponse = new ObjectMapper().readValue(fromValue, ObjectNode.class);
        return new ResponseEntity<>(mockedResponse, HttpStatus.OK);
    }

}
