package tech.fiap.project.infra.mapper;

import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.infra.entity.PaymentEntity;

public class PaymentRepositoryMapper {

	public static PaymentEntity toEntity(Payment payment) {
		if (payment == null) {
			return null;
		}
		else {
			PaymentEntity paymentEntity = new PaymentEntity();
			paymentEntity.setAmount(payment.getAmount());
			paymentEntity.setCurrency(payment.getCurrency());
			paymentEntity.setPaymentDate(payment.getPaymentDate());
			paymentEntity.setPaymentMethod(payment.getPaymentMethod());
			return paymentEntity;
		}
	}

	public static Payment toDomain(PaymentEntity paymentEntity) {
		if (paymentEntity == null) {
			return null;
		}
		else {
			return new Payment(paymentEntity.getPaymentDate(), paymentEntity.getPaymentMethod(),
					paymentEntity.getAmount(), paymentEntity.getCurrency());
		}
	}

}
