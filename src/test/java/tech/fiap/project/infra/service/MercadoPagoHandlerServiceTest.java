package tech.fiap.project.infra.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;
import tech.fiap.project.infra.exception.PaymentNotFound;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MercadoPagoHandlerServiceTest {

	@InjectMocks
	private MercadoPagoHandlerService mercadoPagoHandlerService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_shouldThrowPaymentNotFound_whenNotFoundException() {
		HttpClientErrorException exception = HttpClientErrorException.create(HttpStatusCode.valueOf(404), "Not Found",
				null, null, null);
		Object metadata = "Test Metadata";

		Exception thrownException = assertThrows(PaymentNotFound.class, () -> {
			mercadoPagoHandlerService.execute(exception, metadata);
		});

		assertTrue(thrownException.getMessage().contains(metadata.toString()));
	}

	@Test
	void execute_shouldThrowRuntimeException_whenOtherException() {
		HttpClientErrorException exception = new HttpClientErrorException(HttpStatusCode.valueOf(500),
				"Internal Server Error");
		Object metadata = "Test Metadata";

		Exception thrownException = assertThrows(RuntimeException.class, () -> {
			mercadoPagoHandlerService.execute(exception, metadata);
		});

		assertTrue(thrownException.getMessage().contains("Error: Internal Server Error"));
	}

}