package tech.fiap.project.infra.mapper;

import org.junit.jupiter.api.Test;
import tech.fiap.project.app.dto.StatePayment;
import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.infra.entity.OrderEntity;
import tech.fiap.project.infra.entity.PaymentEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaymentRepositoryMapperTest {

	@Test
	void toEntity_shouldMapPaymentToPaymentEntity() {
		Payment payment = new Payment(LocalDateTime.now(), "Credit Card", BigDecimal.valueOf(100.0),
				Currency.getInstance("BRL"), null, StatePayment.ACCEPTED);
		PaymentEntity paymentEntity = PaymentRepositoryMapper.toEntity(payment);

		assertEquals(payment.getAmount(), paymentEntity.getAmount());
		assertEquals(payment.getCurrency(), paymentEntity.getCurrency());
		assertEquals(payment.getPaymentDate(), paymentEntity.getPaymentDate());
		assertEquals(payment.getPaymentMethod(), paymentEntity.getPaymentMethod());
		assertEquals(payment.getState(), paymentEntity.getState());
	}

	@Test
	void toEntity_shouldReturnNullWhenPaymentIsNull() {
		PaymentEntity paymentEntity = PaymentRepositoryMapper.toEntity(null);
		assertNull(paymentEntity);
	}

	@Test
	void toEntityWithoutOrder_shouldMapPaymentToPaymentEntityWithoutOrder() {
		Payment payment = new Payment(LocalDateTime.now(), "Credit Card", BigDecimal.valueOf(100.0),
				Currency.getInstance("BRL"), null, StatePayment.ACCEPTED);

		PaymentEntity paymentEntity = PaymentRepositoryMapper.toEntityWithoutOrder(payment);

		assertEquals(payment.getAmount(), paymentEntity.getAmount());
		assertEquals(payment.getCurrency(), paymentEntity.getCurrency());
		assertEquals(payment.getPaymentDate(), paymentEntity.getPaymentDate());
		assertEquals(payment.getPaymentMethod(), paymentEntity.getPaymentMethod());
		assertEquals(payment.getState(), paymentEntity.getState());
		assertNull(paymentEntity.getOrder());
	}

	@Test
	void toEntityWithoutOrder_shouldReturnNullWhenPaymentIsNull() {
		PaymentEntity paymentEntity = PaymentRepositoryMapper.toEntityWithoutOrder(null);
		assertNull(paymentEntity);
	}

	@Test
	void toDomainWithoutOrder_shouldMapPaymentEntityToPaymentWithoutOrder() {
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setAmount(BigDecimal.valueOf(100.0));
		paymentEntity.setCurrency(Currency.getInstance("USD"));
		paymentEntity.setPaymentDate(LocalDateTime.now());
		paymentEntity.setPaymentMethod("Credit Card");
		paymentEntity.setState(StatePayment.REJECTED);

		Payment payment = PaymentRepositoryMapper.toDomainWithoutOrder(paymentEntity);

		assertEquals(paymentEntity.getAmount(), payment.getAmount());
		assertEquals(paymentEntity.getCurrency(), payment.getCurrency());
		assertEquals(paymentEntity.getPaymentDate(), payment.getPaymentDate());
		assertEquals(paymentEntity.getPaymentMethod(), payment.getPaymentMethod());
		assertEquals(paymentEntity.getState(), payment.getState());
		assertNull(payment.getOrder());
	}

	@Test
	void toDomainWithoutOrder_shouldReturnNullWhenPaymentEntityIsNull() {
		Payment payment = PaymentRepositoryMapper.toDomainWithoutOrder(null);
		assertNull(payment);
	}

	@Test
	void toDomainWithOrder_shouldMapPaymentEntityToPaymentWithOrder() {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(1L);
		orderEntity.setStatus("PENDING");

		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setAmount(BigDecimal.valueOf(100.0));
		paymentEntity.setCurrency(Currency.getInstance("USD"));
		paymentEntity.setPaymentDate(LocalDateTime.now());
		paymentEntity.setPaymentMethod("Credit Card");
		paymentEntity.setState(StatePayment.REJECTED);
		paymentEntity.setOrder(orderEntity);

		Payment payment = PaymentRepositoryMapper.toDomainWithOrder(paymentEntity);

		assertEquals(paymentEntity.getAmount(), payment.getAmount());
		assertEquals(paymentEntity.getCurrency(), payment.getCurrency());
		assertEquals(paymentEntity.getPaymentDate(), payment.getPaymentDate());
		assertEquals(paymentEntity.getPaymentMethod(), payment.getPaymentMethod());
		assertEquals(paymentEntity.getState(), payment.getState());
		assertEquals(paymentEntity.getOrder().getId(), payment.getOrder().getId());
	}

	@Test
	void toDomainWithOrder_shouldReturnNullWhenPaymentEntityIsNull() {
		Payment payment = PaymentRepositoryMapper.toDomainWithOrder(null);
		assertNull(payment);
	}

}