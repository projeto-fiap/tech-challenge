package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;

public class KitchenStatusException extends BusinessException {

	public KitchenStatusException(Long orderId) {
		super("order.kitchen.status.exception", HttpStatus.BAD_REQUEST, null, orderId.toString());
	}

}
