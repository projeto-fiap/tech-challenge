package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;
import tech.fiap.project.domain.entity.User;

public class UserNotFoundException extends BusinessException {

	public UserNotFoundException(User user) {
		super("user.not.found", HttpStatus.NOT_FOUND, user, user.getId().toString());
	}

	public UserNotFoundException(String id) {
		super("user.not.found", HttpStatus.NOT_FOUND, null, id);
	}
	public UserNotFoundException(String email, String id) {
		super("user.not.found.email", HttpStatus.NOT_FOUND, null, email);
	}

}
