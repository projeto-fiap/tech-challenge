package tech.fiap.project.app.service.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.adapter.KitchenMapper;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.KitchenStatus;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.kitchen.KitchenRetrieveUseCase;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RetrieveOrderServiceTest {

	@Mock
	private RetrieveOrderUseCase retrieveOrderUseCase;

	@Mock
	private KitchenRetrieveUseCase kitchenRetrieveUseCase;

	@InjectMocks
	private RetrieveOrderService retrieveOrderService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findAll_shouldReturnListOfOrderResponseDTO() {
		Order order = new Order(1L, OrderStatus.PAID, LocalDateTime.now(), LocalDateTime.now(), Collections.emptyList(),
				Duration.ZERO, null, null);
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		orderResponseDTO.setId(1L);

		when(retrieveOrderUseCase.findAll()).thenReturn(List.of(order));

		List<OrderResponseDTO> result = retrieveOrderService.findAll();

		assertEquals(1, result.size());
		assertNotNull(result.get(0));
		assertNotNull(result.get(0).getAwaitingTime());
	}

	@Test
	void findById_shouldReturnOrderResponseDTO() {
		Long orderId = 1L;
		Order order = new Order(orderId, OrderStatus.PAID, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Duration.ZERO, null, null);
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		orderResponseDTO.setId(orderId);

		when(retrieveOrderUseCase.findByIdWithPayment(orderId)).thenReturn(Optional.of(order));

		Optional<OrderResponseDTO> result = retrieveOrderService.findById(orderId);

		assertTrue(result.isPresent());
		assertNotNull(result.get().getAwaitingTime());
	}

	@Test
	void findOngoingAll_shouldReturnListOfOngoingOrderResponseDTO() {
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.IN_PRODUCTION);
		Order order = new Order(1L, OrderStatus.PAID, LocalDateTime.now(), LocalDateTime.now(), Collections.emptyList(),
				Duration.ZERO, null, null);
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		orderResponseDTO.setId(1L);
		orderResponseDTO.setKitchenQueue(KitchenMapper.toDTO(kitchen));

		when(kitchenRetrieveUseCase.findAll()).thenReturn(List.of(kitchen));
		when(retrieveOrderUseCase.findAllById(List.of(1L))).thenReturn(List.of(order));

		List<OrderResponseDTO> result = retrieveOrderService.findOngoingAll();

		assertEquals(1, result.size());
		assertNotNull(result.get(0).getAwaitingTime());
	}

	@Test
	void findOngoingAll_shouldSortOrdersByStatusAndDate() {
		Kitchen kitchen1 = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.IN_PRODUCTION);
		Kitchen kitchen2 = new Kitchen(2L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.AWAITING_PRODUCTION);
		Kitchen kitchen3 = new Kitchen(3L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.DONE);

		Order order1 = new Order(1L, OrderStatus.PAID, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Duration.ZERO, null, null);
		Order order2 = new Order(2L, OrderStatus.PAID, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Duration.ZERO, null, null);
		Order order3 = new Order(3L, OrderStatus.PAID, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Duration.ZERO, null, null);

		OrderResponseDTO orderResponseDTO1 = new OrderResponseDTO();
		orderResponseDTO1.setId(1L);
		orderResponseDTO1.setKitchenQueue(KitchenMapper.toDTO(kitchen1));

		OrderResponseDTO orderResponseDTO2 = new OrderResponseDTO();
		orderResponseDTO2.setId(2L);
		orderResponseDTO2.setKitchenQueue(KitchenMapper.toDTO(kitchen2));

		OrderResponseDTO orderResponseDTO3 = new OrderResponseDTO();
		orderResponseDTO3.setId(3L);
		orderResponseDTO3.setKitchenQueue(KitchenMapper.toDTO(kitchen3));

		when(kitchenRetrieveUseCase.findAll()).thenReturn(List.of(kitchen1, kitchen2, kitchen3));
		when(retrieveOrderUseCase.findAllById(List.of(1L, 2L, 3L))).thenReturn(List.of(order1, order2, order3));

		List<OrderResponseDTO> result = retrieveOrderService.findOngoingAll();

		assertEquals(3, result.size());
		assertEquals(orderResponseDTO2.getId(), result.get(0).getId()); // AWAITING_PRODUCTION
		assertEquals(orderResponseDTO1.getId(), result.get(1).getId()); // IN_PRODUCTION
		assertEquals(orderResponseDTO3.getId(), result.get(2).getId()); // DONE
	}

	@Test
	void findOngoingAll_shouldFilterOutFinishedOrders() {
		Kitchen kitchen1 = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.IN_PRODUCTION);
		Kitchen kitchen2 = new Kitchen(2L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.AWAITING_PRODUCTION);

		Order order1 = new Order(1L, OrderStatus.PAID, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Duration.ZERO, null, null);
		Order order2 = new Order(2L, OrderStatus.FINISHED, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Duration.ZERO, null, null);

		OrderResponseDTO orderResponseDTO1 = new OrderResponseDTO();
		orderResponseDTO1.setId(1L);
		orderResponseDTO1.setKitchenQueue(KitchenMapper.toDTO(kitchen1));

		when(kitchenRetrieveUseCase.findAll()).thenReturn(List.of(kitchen1, kitchen2));
		when(retrieveOrderUseCase.findAllById(List.of(1L, 2L))).thenReturn(List.of(order1, order2));

		List<OrderResponseDTO> result = retrieveOrderService.findOngoingAll();

		assertEquals(1, result.size());
		assertEquals(orderResponseDTO1.getId(), result.get(0).getId());
	}

}