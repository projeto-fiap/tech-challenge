package tech.fiap.project.infra.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import tech.fiap.project.infra.exception.PaymentNotFound;

@Service
@AllArgsConstructor
public class MercadoPagoHandlerService {

	public JsonNode execute(HttpClientErrorException exception, Object metadata) {
		String statusCode = exception.getStatusText();
		if (exception instanceof HttpClientErrorException.NotFound) {
			throw new PaymentNotFound(metadata.toString());
		}
		else {
			throw new RuntimeException("Error: " + statusCode);
		}
	}

}
