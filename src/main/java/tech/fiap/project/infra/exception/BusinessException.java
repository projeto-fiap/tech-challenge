package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Locale;
import java.util.ResourceBundle;

public class BusinessException extends RuntimeException {


	private final HttpStatus httpStatusCode;

	private final Object metadata;

	public BusinessException(String key, HttpStatus httpStatusCode, Object metadata, String... args) {
		super(String.format(key, args));
		this.httpStatusCode = httpStatusCode;
		this.metadata = metadata;
	}

	public BusinessException(String key, Throwable cause) {
		super(key, cause);
		this.httpStatusCode = null;
		this.metadata = null;
	}

	public HttpStatusCode getHttpStatusCode() {
		return httpStatusCode;
	}

	public Object getMetadata() {
		return metadata;
	}

}
