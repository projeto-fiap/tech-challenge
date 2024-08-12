package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.PaymentDTO;
import tech.fiap.project.domain.entity.Payment;

public class PaymentMapper {

	public static PaymentDTO toDomain(tech.fiap.project.domain.entity.Payment payment) {
		if (payment == null) {
			return null;
		}
		else {
			PaymentDTO paymentDTO = new PaymentDTO();
			paymentDTO.setAmount(payment.getAmount());
			paymentDTO.setCurrency(payment.getCurrency());
			paymentDTO.setPaymentDate(payment.getPaymentDate());
			paymentDTO.setPaymentMethod(payment.getPaymentMethod());
			return paymentDTO;
		}
	}

	public static Payment toDTO(PaymentDTO payment) {
		if (payment == null) {
			return null;
		}
		else {
			return new Payment(payment.getPaymentDate(), payment.getPaymentMethod(), payment.getAmount(),
					payment.getCurrency());
		}
	}

}
