package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;



public class PersonAlreadyExistsException extends BusinessException {

	private static final String KEY = "Pessoa já existe!";

	public PersonAlreadyExistsException(String documentValue) {
		super(KEY, HttpStatus.CONFLICT, null, documentValue);
	}

	public PersonAlreadyExistsException() {
		super(KEY, HttpStatus.CONFLICT, null);
	}

}