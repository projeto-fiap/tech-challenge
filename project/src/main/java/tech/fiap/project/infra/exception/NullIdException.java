package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;

public class NullIdException extends BusinessException {

	public NullIdException() {
		super("item.id.null", HttpStatus.BAD_REQUEST, null);
	}

}
