package tech.fiap.project.app.service.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.adapter.PaymentMapper;
import tech.fiap.project.app.dto.PaymentDTO;
import tech.fiap.project.app.dto.StatePayment;
import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.domain.usecase.payment.RetrievePaymentUseCase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RetrievePaymentServiceTest {

	@Mock
	private RetrievePaymentUseCase retrievePaymentUseCase;

	@InjectMocks
	private RetrievePaymentService retrievePaymentService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findAll_shouldReturnListOfPaymentDTOs() {
		Payment e1 = new Payment(LocalDateTime.now(), "PIX", BigDecimal.valueOf(100.0), Currency.getInstance("BRL"),
				null, StatePayment.ACCEPTED);
		List<Payment> payments = List.of(e1, e1);
		List<PaymentDTO> paymentDTOs = payments.stream().map(PaymentMapper::toDomain).toList();
		when(retrievePaymentUseCase.findAll()).thenReturn(payments);

		List<PaymentDTO> result = retrievePaymentService.findAll();

		assertEquals(paymentDTOs.size(), result.size());
		verify(retrievePaymentUseCase, times(1)).findAll();
	}

}