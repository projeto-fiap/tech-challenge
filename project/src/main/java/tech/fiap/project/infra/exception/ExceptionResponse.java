package tech.fiap.project.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

	private String message;

	private int code;

	private String status;

	private String metadata;

	@Override
	public String toString() {
		return "{" + "message:'" + message + '\'' + ", code:'" + code + ", status:'" + status + '\'' + ", metadata:'"
				+ metadata + '\'' + '}';
	}

}
