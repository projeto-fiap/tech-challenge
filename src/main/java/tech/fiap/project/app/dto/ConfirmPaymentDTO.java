package tech.fiap.project.app.dto;

import lombok.Data;

@Data
public class ConfirmPaymentDTO {

	private ConfirmPaymentOrderDTO order;

	private StatePayment state;

}
