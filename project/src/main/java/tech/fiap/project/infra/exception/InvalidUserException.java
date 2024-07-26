package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.User;

public class InvalidUserException extends BusinessException {

	public InvalidUserException(Document document) {
		super("user.invalid.document", HttpStatus.BAD_REQUEST, null, document.getType().name(), document.getValue());
	}

	public InvalidUserException(User user) {
		super("user.invalid", HttpStatus.BAD_REQUEST, user);
	}
}
