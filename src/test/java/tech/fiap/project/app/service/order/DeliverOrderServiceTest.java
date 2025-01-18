package tech.fiap.project.app.service.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.KitchenStatus;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.kitchen.KitchenRetrieveUseCase;
import tech.fiap.project.domain.usecase.order.DeliverOrderUseCase;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class DeliverOrderServiceTest {

	@Mock
	private DeliverOrderUseCase deliverOrderUseCase;

	@Mock
	private RetrieveOrderUseCase retrieveOrderUseCase;

	@Mock
	private KitchenRetrieveUseCase kitchenRetrieveUseCase;

	@InjectMocks
	private DeliverOrderService deliverOrderService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_shouldReturnOrderResponseDTO_whenOrderIsDeliveredSuccessfully() {
		Long orderId = 1L;
		Order order = new Order(orderId, OrderStatus.PAID, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Duration.ZERO, null, BigDecimal.ZERO);
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.DONE);

		when(kitchenRetrieveUseCase.findById(orderId)).thenReturn(Optional.of(kitchen));
		when(retrieveOrderUseCase.findByIdWithPayment(orderId)).thenReturn(Optional.of(order));
		when(deliverOrderUseCase.execute(orderId)).thenReturn(order);

		OrderResponseDTO result = deliverOrderService.execute(orderId);

		assertNotNull(result);
	}

}