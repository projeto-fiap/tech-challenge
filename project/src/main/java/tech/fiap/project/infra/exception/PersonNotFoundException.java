package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;
import tech.fiap.project.domain.entity.Person;

public class PersonNotFoundException extends BusinessException {

	public PersonNotFoundException(Person person) {
		super("user.not.found", HttpStatus.NOT_FOUND, person, person.getId().toString());
	}

	public PersonNotFoundException(String id) {
		super("user.not.found", HttpStatus.NOT_FOUND, null, id);
	}

}
