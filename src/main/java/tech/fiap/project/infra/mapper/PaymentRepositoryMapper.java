package tech.fiap.project.infra.mapper;

import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.infra.entity.OrderEntity;
import tech.fiap.project.infra.entity.PaymentEntity;

public class PaymentRepositoryMapper {

	private PaymentRepositoryMapper(){

	}

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
			paymentEntity.setState(payment.getState());
			if (payment.getOrder() != null) {
				paymentEntity.setOrder(OrderRepositoryMapper.toEntity(payment.getOrder()));
			}
			return paymentEntity;
		}
	}

	public static PaymentEntity toEntityWithoutOrder(Payment payment) {
		if (payment == null) {
			return null;
		}
		else {
			PaymentEntity paymentEntity = new PaymentEntity();
			paymentEntity.setAmount(payment.getAmount());
			paymentEntity.setCurrency(payment.getCurrency());
			paymentEntity.setPaymentDate(payment.getPaymentDate());
			paymentEntity.setPaymentMethod(payment.getPaymentMethod());
			paymentEntity.setState(payment.getState());
			return paymentEntity;
		}
	}

	public static Payment toDomainWithoutOrder(PaymentEntity paymentEntity) {
		if (paymentEntity == null) {
			return null;
		}
		else {
			return new Payment(paymentEntity.getPaymentDate(), paymentEntity.getPaymentMethod(),
					paymentEntity.getAmount(), paymentEntity.getCurrency(), null, paymentEntity.getState());
		}
	}

	public static Payment toDomainWithOrder(PaymentEntity paymentEntity) {
		if (paymentEntity == null) {
			return null;
		}
		else {
			OrderEntity order = paymentEntity.getOrder();
			Order domain = null;
			if (order != null) {
				order.setPayments(null);
				domain = OrderRepositoryMapper.toDomain(order);
			}
			return new Payment(paymentEntity.getPaymentDate(), paymentEntity.getPaymentMethod(),
					paymentEntity.getAmount(), paymentEntity.getCurrency(), domain, paymentEntity.getState());
		}
	}

}
