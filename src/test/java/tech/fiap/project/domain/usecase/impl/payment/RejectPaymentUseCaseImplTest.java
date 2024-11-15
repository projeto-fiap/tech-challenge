package tech.fiap.project.domain.usecase.impl.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.dto.StatePayment;
import tech.fiap.project.domain.dataprovider.OrderDataProvider;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.entity.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RejectPaymentUseCaseImplTest {

	@Mock
	private OrderDataProvider orderDataProvider;

	@InjectMocks
	private RejectPaymentUseCaseImpl rejectPaymentUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void rejectPayment_rejectsPaymentSuccessfully() {
		Order order = new Order(1L, OrderStatus.AWAITING_PAYMENT, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Collections.emptyList(), null, null, BigDecimal.TEN);
		when(orderDataProvider.retrieveById(1L)).thenReturn(Optional.of(order));

		Payment payment = rejectPaymentUseCaseImpl.rejectPayment(1L);

		assertEquals(OrderStatus.AWAITING_PAYMENT, order.getStatus());
		assertEquals(StatePayment.REJECTED, payment.getState());
		assertEquals(order.getTotalPrice(), payment.getAmount());
		verify(orderDataProvider).create(order);
	}

}