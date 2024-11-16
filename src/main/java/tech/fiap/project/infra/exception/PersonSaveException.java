package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;

public class PersonSaveException extends BusinessException {

	public PersonSaveException(String key, HttpStatus httpStatus, Object metadata, String... args) {
		super(key, httpStatus, metadata, args);
	}

	public PersonSaveException(String documentValue) {
		super("person.save.error", HttpStatus.CONFLICT, null, documentValue);
	}

}
