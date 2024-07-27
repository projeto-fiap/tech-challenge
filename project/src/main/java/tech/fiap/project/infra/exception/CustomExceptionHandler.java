package tech.fiap.project.infra.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ BusinessException.class })
	protected ResponseEntity<Object> handleConflict(BusinessException ex, WebRequest request) {
		HttpStatusCode httpStatusCode = ex.getHttpStatusCode();
		Object metadata = ex.getMetadata();
		ExceptionResponse response;
		if (metadata == null) {
			response = new ExceptionResponse(ex.getMessage(), httpStatusCode.value(), httpStatusCode.toString(), null);
		}
		else {
			response = new ExceptionResponse(ex.getMessage(), httpStatusCode.value(), httpStatusCode.toString(),
					metadata.toString());
		}
		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatusCode, request);
	}

}
