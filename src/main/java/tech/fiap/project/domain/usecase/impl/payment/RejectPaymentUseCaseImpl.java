package tech.fiap.project.domain.usecase.impl.payment;

import tech.fiap.project.app.dto.StatePayment;
import tech.fiap.project.domain.dataprovider.OrderDataProvider;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.domain.usecase.payment.RejectPaymentUseCase;
import tech.fiap.project.infra.exception.OrderNotFound;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class RejectPaymentUseCaseImpl implements RejectPaymentUseCase {

	private final OrderDataProvider orderDataProvider;

	public RejectPaymentUseCaseImpl(OrderDataProvider orderDataProvider) {
		this.orderDataProvider = orderDataProvider;
	}

	@Override
	public Payment rejectPayment(Long orderId) {
		Optional<Order> order = orderDataProvider.retrieveById(orderId);
		if (order.isPresent() && order.get().getStatus().equals(OrderStatus.AWAITING_PAYMENT)) {
			AtomicReference<Payment> newPayment = new AtomicReference<>();
			order.ifPresent(order1 -> newPayment.set(this.setPayment(order1)));
			order.ifPresent(this::saveOrder);
			return newPayment.get();
		}
		else {
			throw new OrderNotFound(orderId);
		}
	}

	private Payment setPayment(Order order) {
		LocalDateTime now = LocalDateTime.now();
		Currency currency = Currency.getInstance("BRL");
		Payment payment = new Payment(now, "PIX", order.getTotalPrice(), currency, null, StatePayment.REJECTED);
		List<Payment> payments = order.getPayments();
		if (payments == null || payments.isEmpty()) {
			payments = new ArrayList<>();
		}
		payments.add(payment);
		order.setPayments(payments);
		order.setStatus(OrderStatus.AWAITING_PAYMENT);
		return payment;
	}

	private void saveOrder(Order order) {
		orderDataProvider.create(order);
	}

}
