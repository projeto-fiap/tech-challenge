package tech.fiap.project.app.service.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CheckoutOrderServiceTest {

	@Mock
	private RetrieveOrderUseCase retrieveOrderUseCase;

	@InjectMocks
	private CheckoutOrderService checkoutOrderService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_shouldReturnOrderResponseDTO_whenOrderIsPaid() {
		Long orderId = 1L;
		Order order = new Order(orderId, OrderStatus.PAID, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Collections.emptyList(), Duration.ZERO, null, BigDecimal.ZERO);
		OrderResponseDTO expectedOrderResponseDTO = new OrderResponseDTO();
		expectedOrderResponseDTO.setId(orderId);

		when(retrieveOrderUseCase.findByIdWithPayment(orderId)).thenReturn(Optional.of(order));

		Optional<OrderResponseDTO> result = checkoutOrderService.execute(orderId);

		assertTrue(result.isPresent());
	}

	@Test
	void execute_shouldReturnEmpty_whenOrderIsNotPaid() {
		Long orderId = 1L;
		Order order = new Order(orderId, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Collections.emptyList(), Duration.ZERO, null, BigDecimal.ZERO);

		when(retrieveOrderUseCase.findByIdWithPayment(orderId)).thenReturn(Optional.of(order));

		Optional<OrderResponseDTO> result = checkoutOrderService.execute(orderId);

		assertFalse(result.isPresent());
	}

	@Test
	void execute_shouldReturnEmpty_whenOrderDoesNotExist() {
		Long orderId = 1L;

		when(retrieveOrderUseCase.findByIdWithPayment(orderId)).thenReturn(Optional.empty());

		Optional<OrderResponseDTO> result = checkoutOrderService.execute(orderId);

		assertFalse(result.isPresent());
	}

}