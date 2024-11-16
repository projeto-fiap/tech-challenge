package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;

public class GenerateQrCodeException extends BusinessException {

	public GenerateQrCodeException(String message) {
		super("qrcode.error", HttpStatus.INTERNAL_SERVER_ERROR, null, message);
	}

}
