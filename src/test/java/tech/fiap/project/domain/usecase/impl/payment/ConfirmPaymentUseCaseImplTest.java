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
import tech.fiap.project.infra.exception.OrderNotFound;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class ConfirmPaymentUseCaseImplTest {

	@Mock
	private OrderDataProvider orderDataProvider;

	@InjectMocks
	private ConfirmPaymentUseCaseImpl confirmPaymentUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void confirmPayment_confirmsPaymentSuccessfully() {
		Order order = new Order(1L, OrderStatus.AWAITING_PAYMENT, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Collections.emptyList(), null, null, BigDecimal.TEN);
		when(orderDataProvider.retrieveByIdWithPayment(1L)).thenReturn(Optional.of(order));

		Payment payment = confirmPaymentUseCaseImpl.confirmPayment(1L);

		assertEquals(OrderStatus.PAID, order.getStatus());
		assertEquals(StatePayment.ACCEPTED, payment.getState());
		assertEquals(order, payment.getOrder());
	}

	@Test
	void confirmPayment_throwsOrderNotFoundException() {
		when(orderDataProvider.retrieveByIdWithPayment(1L)).thenReturn(Optional.empty());

		assertThrows(OrderNotFound.class, () -> confirmPaymentUseCaseImpl.confirmPayment(1L));
	}

}