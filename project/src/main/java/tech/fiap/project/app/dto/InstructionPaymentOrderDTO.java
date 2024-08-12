package tech.fiap.project.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class InstructionPaymentOrderDTO {

	private String id;

	private String description;

	private String externalReference;

	private String title;

	private String notificationUrl;

	private String qrData;

	private BigDecimal amount;

}