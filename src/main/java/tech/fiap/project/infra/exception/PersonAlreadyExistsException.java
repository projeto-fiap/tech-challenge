package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;

public class PersonAlreadyExistsException extends BusinessException {

	public PersonAlreadyExistsException(String key, HttpStatus httpStatus, Object metadata, String... args) {
		super(key, httpStatus, metadata, args);
	}

	public PersonAlreadyExistsException(String documentValue) {
		super("document.already.exists", HttpStatus.CONFLICT, null, documentValue);
	}

}
