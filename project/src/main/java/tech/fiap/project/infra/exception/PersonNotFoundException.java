package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;
import tech.fiap.project.domain.entity.Person;

import java.util.Optional;

public class PersonNotFoundException extends BusinessException {

	public PersonNotFoundException(Person person) {
		super("person.not.found", HttpStatus.NOT_FOUND, person, person.getId().toString());
	}

	public PersonNotFoundException(String id) {
		super("person.not.found", HttpStatus.NOT_FOUND, null, id);
	}

	public PersonNotFoundException(Optional<String> email) {
		super("user.not.found.email", HttpStatus.NOT_FOUND, null, email.orElse(""));
	}

}
