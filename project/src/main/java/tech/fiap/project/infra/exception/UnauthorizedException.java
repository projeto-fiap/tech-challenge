package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BusinessException {

	public UnauthorizedException(String key, HttpStatus httpStatus, Object metadata, String... args) {
		super(key, httpStatus, metadata, args);
	}

	public UnauthorizedException(String key, Throwable cause) {
		super(key, cause);
	}

	public UnauthorizedException(String message, HttpStatus httpStatus) {
		super("not.permission", httpStatus, null);
	}

}
