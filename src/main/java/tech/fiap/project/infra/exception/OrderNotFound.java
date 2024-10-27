package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFound extends BusinessException {

	public OrderNotFound(Long orderId) {
		super("order.not.found", HttpStatus.NOT_FOUND, null, orderId.toString());
	}

}
