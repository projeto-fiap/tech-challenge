package tech.fiap.project.domain.usecase.impl.payment;

import tech.fiap.project.domain.dataprovider.OrderDataProvider;
import tech.fiap.project.domain.dataprovider.PaymentDataProvider;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.domain.usecase.payment.RetrievePaymentUseCase;

import java.util.List;
import java.util.Optional;

public class RetrievePaymentUseCaseImpl implements RetrievePaymentUseCase {

	private final PaymentDataProvider paymentDataProvider;

	public RetrievePaymentUseCaseImpl(PaymentDataProvider paymentDataProvider) {
		this.paymentDataProvider = paymentDataProvider;
	}

	@Override
	public List<Payment> findAll() {
		return paymentDataProvider.retrieveAll();
	}

	@Override
	public Optional<Payment> findById(Long id) {
		return paymentDataProvider.retrieveById(id);
	}

}
