package tech.fiap.project.domain.usecase.impl;

import tech.fiap.project.domain.dataprovider.OrderDataProvider;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.domain.usecase.payment.ConfirmPaymentUseCase;
import tech.fiap.project.infra.exception.OrderNotFound;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Optional;

public class ConfirmPaymentUseCaseImpl implements ConfirmPaymentUseCase {

	private final OrderDataProvider orderDataProvider;

	public ConfirmPaymentUseCaseImpl(OrderDataProvider orderDataProvider) {
		this.orderDataProvider = orderDataProvider;
	}

	@Override
	public Payment confirmPayment(Long orderId) {
		Optional<Order> order = orderDataProvider.retrieveById(orderId);
		if (order.isPresent() && order.get().getStatus().equals(OrderStatus.AWAITING_PAYMENT)) {
			order.ifPresent(this::setPaid);
			order.ifPresent(this::saveOrder);
			return order.get().getPayment();
		}
		else {
			throw new OrderNotFound(orderId);
		}
	}

	private void setPaid(Order order) {
		var now = LocalDateTime.now();
		var currency = Currency.getInstance("BRL");
		var payment = new Payment(now, "PIX", order.getTotalPrice(), currency);
		order.setPayment(payment);
		order.setStatus(OrderStatus.PAID);
	}

	private void saveOrder(Order order) {
		orderDataProvider.create(order);
	}

}
