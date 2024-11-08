package tech.fiap.project.app.service.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.dto.ConfirmPaymentDTO;
import tech.fiap.project.app.dto.ConfirmPaymentOrderDTO;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.domain.usecase.payment.ConfirmPaymentUseCase;
import tech.fiap.project.domain.usecase.payment.RejectPaymentUseCase;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tech.fiap.project.app.dto.StatePayment.ACCEPTED;
import static tech.fiap.project.app.dto.StatePayment.REJECTED;

class ConfirmPaymentDTOServiceTest {

	@Mock
	private ConfirmPaymentUseCase confirmPaymentUseCase;

	@Mock
	private RejectPaymentUseCase rejectPaymentUseCase;

	@InjectMocks
	private ConfirmPaymentDTOService confirmPaymentDTOService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void confirmPayment_shouldReturnAcceptedPaymentDTO() {
		ConfirmPaymentDTO confirmPaymentDTO = new ConfirmPaymentDTO();
		confirmPaymentDTO.setState(ACCEPTED);
		ConfirmPaymentOrderDTO orderDTO = new ConfirmPaymentOrderDTO();
		orderDTO.setId(1L);
		confirmPaymentDTO.setOrder(orderDTO);
		List<Item> itemList = List.of(new Item(1L, "Item 1", BigDecimal.TEN, BigDecimal.TEN, "unit", ItemCategory.FOOD,
				new ArrayList<>(), "", ""));
		Order order = new Order(1L, null, null, null, itemList, null, null, null, null);
		Payment expectedPayment = new Payment(LocalDateTime.now(), "PIX", BigDecimal.TEN, Currency.getInstance("BRL"),
				order, ACCEPTED);
		when(confirmPaymentUseCase.confirmPayment(anyLong())).thenReturn(expectedPayment);
		confirmPaymentDTOService.confirmPayment(confirmPaymentDTO);
		verify(confirmPaymentUseCase).confirmPayment(1L);
	}

	@Test
	void confirmPayment_shouldReturnRejectedPaymentDTO() {
		ConfirmPaymentDTO confirmPaymentDTO = new ConfirmPaymentDTO();
		confirmPaymentDTO.setState(REJECTED);
		ConfirmPaymentOrderDTO orderDTO = new ConfirmPaymentOrderDTO();
		orderDTO.setId(1L);
		confirmPaymentDTO.setOrder(orderDTO);
		List<Item> itemList = List.of(new Item(1L, "Item 1", BigDecimal.TEN, BigDecimal.TEN, "unit", ItemCategory.FOOD,
				new ArrayList<>(), "", ""));
		Order order = new Order(1L, null, null, null, itemList, null, null, null, null);
		Payment expectedPayment = new Payment(LocalDateTime.now(), "PIX", BigDecimal.TEN, Currency.getInstance("BRL"),
				order, ACCEPTED);
		when(rejectPaymentUseCase.rejectPayment(anyLong())).thenReturn(expectedPayment);

		confirmPaymentDTOService.confirmPayment(confirmPaymentDTO);
		verify(rejectPaymentUseCase).rejectPayment(1L);
	}

}