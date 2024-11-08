package tech.fiap.project.infra.dataprovider;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.infra.entity.OrderEntity;
import tech.fiap.project.infra.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderDataProviderImplTest {

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderDataProviderImpl orderDataProvider;

	public OrderDataProviderImplTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void retrieveAll_shouldReturnEmptyOptional_whenOrderIdIsNull() {
		Order order = new Order(null, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Collections.emptyList(), Duration.ZERO, null, BigDecimal.ZERO);
		;
		Optional<Order> result = orderDataProvider.retrieveAll(order);
		assertFalse(result.isPresent());
		verify(orderRepository, never()).findById(anyLong());
	}

	@Test
	void retrieveAll_shouldReturnOrder_whenOrderIdIsNotNull() {
		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(),
				Collections.emptyList(), Collections.emptyList(), Duration.ZERO, null, BigDecimal.ZERO);
		;
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(1L);
		when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));

		Optional<Order> result = orderDataProvider.retrieveAll(order);

		assertTrue(result.isPresent());
		assertEquals(order.getId(), result.get().getId());
		verify(orderRepository, times(1)).findById(1L);
	}

	@Test
	void retrieveAll_shouldReturnListOfOrders() {
		List<OrderEntity> orderEntities = List.of(new OrderEntity(), new OrderEntity());
		when(orderRepository.findAll()).thenReturn(orderEntities);

		List<Order> orders = orderDataProvider.retrieveAll();

		assertEquals(orderEntities.size(), orders.size());
		verify(orderRepository, times(1)).findAll();
	}

	@Test
	void retrieveAllById_shouldReturnListOfOrders() {
		List<OrderEntity> orderEntities = List.of(new OrderEntity(), new OrderEntity());
		List<Long> ids = List.of(1L, 2L);
		when(orderRepository.findAllById(ids)).thenReturn(orderEntities);

		List<Order> orders = orderDataProvider.retrieveAllById(ids);

		assertEquals(orderEntities.size(), orders.size());
		verify(orderRepository, times(1)).findAllById(ids);
	}

	@Test
	void create_shouldSaveAndReturnOrder() {
		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(), List.of(), List.of(),
				Duration.ZERO, null, null);
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(1L);
		when(orderRepository.save(any())).thenReturn(orderEntity);

		Order savedOrder = orderDataProvider.create(order);

		assertEquals(orderEntity.getId(), savedOrder.getId());
		verify(orderRepository, times(1)).save(any());
	}

	@Test
	void retrieveById_shouldReturnOrder() {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(1L);
		when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));

		Optional<Order> order = orderDataProvider.retrieveById(1L);

		assertTrue(order.isPresent());
		assertEquals(orderEntity.getId(), order.get().getId());
		verify(orderRepository, times(1)).findById(1L);
	}

	@Test
	void retrieveByIdWithPayment_shouldReturnOrder() {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(1L);
		when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));

		Optional<Order> order = orderDataProvider.retrieveByIdWithPayment(1L);

		assertTrue(order.isPresent());
		assertEquals(orderEntity.getId(), order.get().getId());
		verify(orderRepository, times(1)).findById(1L);
	}

}