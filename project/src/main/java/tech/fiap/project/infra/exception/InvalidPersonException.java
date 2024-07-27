package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.Person;

public class InvalidPersonException extends BusinessException {

	public InvalidPersonException(Document document) {
		super("user.invalid.document", HttpStatus.BAD_REQUEST, null, document.getType().name(), document.getValue());
	}

	public InvalidPersonException(Person person) {
		super("user.invalid", HttpStatus.BAD_REQUEST, person);
	}

}
