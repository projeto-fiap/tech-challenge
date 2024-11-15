package tech.fiap.project.infra.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tech.fiap.project.app.dto.PaymentResponseDTO;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.impl.order.CalculateTotalOrderUseCaseImpl;
import tech.fiap.project.infra.configuration.MercadoPagoProperties;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreatePaymentUrlUseCaseMercadoPagoServiceTest {

	@Mock
	private RestTemplate restTemplateMercadoPago;

	@Mock
	private MercadoPagoProperties mercadoPagoProperties;

	@Mock
	private CalculateTotalOrderUseCaseImpl calculateTotalOrderUseCaseImpl;

	@InjectMocks
	private CreatePaymentUrlUseCaseMercadoPagoService createPaymentUrlUseCaseMercadoPagoService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_shouldReturnQrData_whenSuccessful() {
		Item item1 = new Item(1L, "Hamburguer", BigDecimal.valueOf(45.5), BigDecimal.valueOf(100.0), "gramas",
				ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne", "https://www.google.com");

		Order order = new Order(1L, null, null, null, List.of(item1), null, null, null, null);

		String expectedQrData = "qrData";
		PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
		paymentResponseDTO.setQrData(expectedQrData);

		when(mercadoPagoProperties.getAccessToken()).thenReturn("token");
		when(mercadoPagoProperties.getUserId()).thenReturn("userId");
		when(mercadoPagoProperties.getPos()).thenReturn("pos");
		when(calculateTotalOrderUseCaseImpl.execute(any())).thenReturn(BigDecimal.valueOf(100.0));
		when(restTemplateMercadoPago.exchange(any(RequestEntity.class), any(Class.class)))
				.thenReturn(ResponseEntity.ok(paymentResponseDTO));

		String qrData = createPaymentUrlUseCaseMercadoPagoService.execute(order);

		assertEquals(expectedQrData, qrData);
	}

}