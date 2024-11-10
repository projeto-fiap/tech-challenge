package tech.fiap.project.app.adapter;

import org.junit.jupiter.api.Test;
import tech.fiap.project.app.dto.OrderResponsePaymentDTO;
import tech.fiap.project.app.dto.PaymentDTO;
import tech.fiap.project.app.dto.StatePayment;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.domain.entity.Order;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMapperTest {

	@Test
	void toDomain_shouldMapPaymentToPaymentDTO() {
		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(), List.of(), null,
				Duration.ZERO, null, BigDecimal.TEN);
		Payment payment = new Payment(LocalDateTime.now(), "CREDIT_CARD", BigDecimal.TEN, Currency.getInstance("USD"),
				order, StatePayment.ACCEPTED);

		PaymentDTO paymentDTO = PaymentMapper.toDomain(payment);

		assertNotNull(paymentDTO);
		assertEquals(payment.getAmount(), paymentDTO.getAmount());
		assertEquals(payment.getCurrency(), paymentDTO.getCurrency());
		assertEquals(payment.getPaymentDate(), paymentDTO.getPaymentDate());
		assertEquals(payment.getPaymentMethod(), paymentDTO.getPaymentMethod());
		assertEquals(payment.getState(), paymentDTO.getState());
		assertNotNull(paymentDTO.getOrder());
	}

	@Test
	void toDomainWithoutOrder_shouldMapPaymentToPaymentDTOWithoutOrder() {
		Payment payment = new Payment(LocalDateTime.now(), "CREDIT_CARD", BigDecimal.TEN, Currency.getInstance("USD"),
				null, StatePayment.ACCEPTED);

		PaymentDTO paymentDTO = PaymentMapper.toDomainWithoutOrder(payment);

		assertNotNull(paymentDTO);
		assertEquals(payment.getAmount(), paymentDTO.getAmount());
		assertEquals(payment.getCurrency(), paymentDTO.getCurrency());
		assertEquals(payment.getPaymentDate(), paymentDTO.getPaymentDate());
		assertEquals(payment.getPaymentMethod(), paymentDTO.getPaymentMethod());
		assertEquals(payment.getState(), paymentDTO.getState());
		assertNull(paymentDTO.getOrder());
	}

	@Test
	void toDTO_shouldMapPaymentDTOToPayment() {
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setAmount(BigDecimal.TEN);
		paymentDTO.setCurrency(Currency.getInstance("USD"));
		paymentDTO.setPaymentDate(LocalDateTime.now());
		paymentDTO.setPaymentMethod("CREDIT_CARD");
		paymentDTO.setState(StatePayment.REJECTED);
		paymentDTO.setOrder(new OrderResponsePaymentDTO(1L, OrderStatus.PENDING, LocalDateTime.now(),
				LocalDateTime.now(), List.of(), null, Duration.ZERO, BigDecimal.TEN));

		Payment payment = PaymentMapper.toDTO(paymentDTO);

		assertNotNull(payment);
		assertEquals(paymentDTO.getAmount(), payment.getAmount());
		assertEquals(paymentDTO.getCurrency(), payment.getCurrency());
		assertEquals(paymentDTO.getPaymentDate(), payment.getPaymentDate());
		assertEquals(paymentDTO.getPaymentMethod(), payment.getPaymentMethod());
		assertEquals(paymentDTO.getState(), payment.getState());
		assertNotNull(payment.getOrder());
	}

}