package tech.fiap.project.app.service.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.adapter.KitchenMapper;
import tech.fiap.project.app.adapter.OrderMapper;
import tech.fiap.project.app.dto.KitchenDTO;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.KitchenStatus;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.kitchen.KitchenRetrieveUseCase;
import tech.fiap.project.domain.usecase.order.DeliverOrderUseCase;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;
import tech.fiap.project.infra.exception.KitchenStatusException;
import tech.fiap.project.infra.exception.OrderNotFound;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
				Collections.emptyList(), Collections.emptyList(), Duration.ZERO, null, BigDecimal.ZERO);
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.DONE);

		when(kitchenRetrieveUseCase.findById(orderId)).thenReturn(Optional.of(kitchen));
		when(retrieveOrderUseCase.findByIdWithPayment(orderId)).thenReturn(Optional.of(order));
		when(deliverOrderUseCase.execute(orderId)).thenReturn(order);

		OrderResponseDTO result = deliverOrderService.execute(orderId);

		assertNotNull(result);
	}

	@Test
	void execute_shouldThrowKitchenStatusException_whenKitchenStatusIsNotDone() {
		Long orderId = 1L;
		Order order = new Order(orderId, OrderStatus.PAID, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Collections.emptyList(), Duration.ZERO, null, BigDecimal.ZERO);
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.IN_PRODUCTION);
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		orderResponseDTO.setId(orderId);
		orderResponseDTO.setKitchenQueue(KitchenMapper.toDTO(kitchen));

		when(kitchenRetrieveUseCase.findById(orderId)).thenReturn(Optional.of(kitchen));
		when(retrieveOrderUseCase.findByIdWithPayment(orderId)).thenReturn(Optional.of(order));

		assertThrows(KitchenStatusException.class, () -> deliverOrderService.execute(orderId));
	}

	@Test
	void execute_shouldThrowOrderNotFound_whenOrderStatusIsFinished() {
		Long orderId = 1L;
		Order order = new Order(orderId, OrderStatus.FINISHED, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Collections.emptyList(), Duration.ZERO, null, BigDecimal.ZERO);
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.DONE);
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		orderResponseDTO.setId(orderId);
		orderResponseDTO.setKitchenQueue(KitchenMapper.toDTO(kitchen));

		when(kitchenRetrieveUseCase.findById(orderId)).thenReturn(Optional.of(kitchen));
		when(retrieveOrderUseCase.findByIdWithPayment(orderId)).thenReturn(Optional.of(order));

		assertThrows(OrderNotFound.class, () -> deliverOrderService.execute(orderId));
	}

	@Test
	void execute_shouldThrowOrderNotFound_whenOrderDoesNotExist() {
		Long orderId = 1L;

		when(retrieveOrderUseCase.findByIdWithPayment(orderId)).thenReturn(Optional.empty());

		assertThrows(OrderNotFound.class, () -> deliverOrderService.execute(orderId));
	}

}