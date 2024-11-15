package tech.fiap.project.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDTO {

	@JsonProperty("qr_data")
	private String qrData;

}