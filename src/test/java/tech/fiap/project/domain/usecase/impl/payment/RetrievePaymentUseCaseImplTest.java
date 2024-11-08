package tech.fiap.project.domain.usecase.impl.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.dataprovider.PaymentDataProvider;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.app.dto.StatePayment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class RetrievePaymentUseCaseImplTest {

	@Mock
	private PaymentDataProvider paymentDataProvider;

	@InjectMocks
	private RetrievePaymentUseCaseImpl retrievePaymentUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findAll_returnsAllPayments() {
		Order order = new Order(1L, null, LocalDateTime.now(), LocalDateTime.now(), null, null, null, null,
				BigDecimal.TEN);
		Payment payment1 = new Payment(LocalDateTime.now(), "Credit Card", BigDecimal.TEN, Currency.getInstance("USD"),
				order, StatePayment.ACCEPTED);
		Payment payment2 = new Payment(LocalDateTime.now(), "Debit Card", BigDecimal.valueOf(20),
				Currency.getInstance("USD"), order, StatePayment.REJECTED);
		when(paymentDataProvider.retrieveAll()).thenReturn(Arrays.asList(payment1, payment2));

		List<Payment> result = retrievePaymentUseCaseImpl.findAll();

		assertEquals(2, result.size());
		assertTrue(result.contains(payment1));
		assertTrue(result.contains(payment2));
	}

	@Test
	void findById_returnsPaymentById() {
		Order order = new Order(1L, null, LocalDateTime.now(), LocalDateTime.now(), null, null, null, null,
				BigDecimal.TEN);
		Payment payment = new Payment(LocalDateTime.now(), "Credit Card", BigDecimal.TEN, Currency.getInstance("USD"),
				order, StatePayment.ACCEPTED);
		when(paymentDataProvider.retrieveById(1L)).thenReturn(Optional.of(payment));

		Optional<Payment> result = retrievePaymentUseCaseImpl.findById(1L);

		assertTrue(result.isPresent());
		assertEquals(payment, result.get());
	}

}