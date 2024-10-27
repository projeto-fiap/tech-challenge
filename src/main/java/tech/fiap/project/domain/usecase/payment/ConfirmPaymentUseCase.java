package tech.fiap.project.domain.usecase.payment;

import tech.fiap.project.domain.entity.Payment;

public interface ConfirmPaymentUseCase {

	Payment confirmPayment(Long orderId);

}
