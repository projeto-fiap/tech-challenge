package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.Person;

public class InvalidPersonException extends BusinessException {

	public InvalidPersonException(Document document) {
		super("person.invalid.document", HttpStatus.BAD_REQUEST, null, document.getType().name(), document.getValue());
	}

	public InvalidPersonException(Person person) {
		super("person.invalid", HttpStatus.BAD_REQUEST, person);
	}

}
