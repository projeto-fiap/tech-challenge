package tech.fiap.project.infra.dataprovider;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.infra.entity.PaymentEntity;
import tech.fiap.project.infra.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PaymentDataProviderImplTest {

	@Mock
	private PaymentRepository paymentRepository;

	@InjectMocks
	private PaymentDataProviderImpl paymentDataProvider;

	public PaymentDataProviderImplTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void retrieveAll_shouldReturnListOfPayments() {
		List<PaymentEntity> paymentEntities = List.of(new PaymentEntity(), new PaymentEntity());
		when(paymentRepository.findAll()).thenReturn(paymentEntities);

		List<Payment> payments = paymentDataProvider.retrieveAll();

		assertEquals(paymentEntities.size(), payments.size());
		verify(paymentRepository, times(1)).findAll();
	}

	@Test
	void retrieveById_shouldReturnPayment() {
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setId(1L);
		when(paymentRepository.findById(1L)).thenReturn(Optional.of(paymentEntity));

		Optional<Payment> payment = paymentDataProvider.retrieveById(1L);

		assertTrue(payment.isPresent());
		verify(paymentRepository, times(1)).findById(1L);
	}

}