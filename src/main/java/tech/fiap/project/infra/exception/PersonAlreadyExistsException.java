package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;

import java.util.Locale;
import java.util.ResourceBundle;

public class PersonAlreadyExistsException extends BusinessException {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("error_messages", Locale.ENGLISH); // Use a specific locale!
	private static final String KEY = "person.already.exists"; // Define the key as a constant

	public PersonAlreadyExistsException(String documentValue) {
		super(KEY, HttpStatus.CONFLICT, null, documentValue); // Use the key and arguments
	}

	public PersonAlreadyExistsException() {
		super(KEY, HttpStatus.CONFLICT, null);
	}
}