package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;

public class OrderStatusException extends BusinessException {

	public OrderStatusException(Long orderId) {
		super("order.status.exception", HttpStatus.BAD_REQUEST, null, orderId.toString());
	}

}
