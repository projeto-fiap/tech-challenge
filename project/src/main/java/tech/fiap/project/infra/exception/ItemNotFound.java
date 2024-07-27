package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;

public class ItemNotFound extends BusinessException {

	public ItemNotFound(Long itemId) {
		super("item.not.found", HttpStatus.NOT_FOUND, null, itemId.toString());
	}

}
