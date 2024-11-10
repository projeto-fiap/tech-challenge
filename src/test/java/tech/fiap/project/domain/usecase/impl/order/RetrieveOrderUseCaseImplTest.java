package tech.fiap.project.domain.usecase.impl.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.dataprovider.OrderDataProvider;
import tech.fiap.project.domain.entity.Order;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class RetrieveOrderUseCaseImplTest {

	@Mock
	private OrderDataProvider orderDataProvider;

	@InjectMocks
	private RetrieveOrderUseCaseImpl retrieveOrderUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findAll_returnsAllOrders() {
		Order order1 = new Order(1L, null, null, null, null, null, null, null, null);
		Order order2 = new Order(2L, null, null, null, null, null, null, null, null);
		when(orderDataProvider.retrieveAll()).thenReturn(Arrays.asList(order1, order2));

		List<Order> result = retrieveOrderUseCaseImpl.findAll();

		assertEquals(2, result.size());
		assertTrue(result.contains(order1));
		assertTrue(result.contains(order2));
	}

	@Test
	void findAllById_returnsOrdersByIds() {
		Order order1 = new Order(1L, null, null, null, null, null, null, null, null);
		Order order2 = new Order(2L, null, null, null, null, null, null, null, null);
		when(orderDataProvider.retrieveAll()).thenReturn(Arrays.asList(order1, order2));

		List<Order> result = retrieveOrderUseCaseImpl.findAllById(Arrays.asList(1L, 2L));

		assertEquals(2, result.size());
		assertTrue(result.contains(order1));
		assertTrue(result.contains(order2));
	}

	@Test
	void findById_returnsOrderById() {
		Order order = new Order(1L, null, null, null, null, null, null, null, null);
		when(orderDataProvider.retrieveById(1L)).thenReturn(Optional.of(order));

		Optional<Order> result = retrieveOrderUseCaseImpl.findById(1L);

		assertTrue(result.isPresent());
		assertEquals(order, result.get());
	}

	@Test
	void findByIdWithPayment_returnsOrderByIdWithPayment() {
		Order order = new Order(1L, null, null, null, null, null, null, null, null);
		when(orderDataProvider.retrieveByIdWithPayment(1L)).thenReturn(Optional.of(order));

		Optional<Order> result = retrieveOrderUseCaseImpl.findByIdWithPayment(1L);

		assertTrue(result.isPresent());
		assertEquals(order, result.get());
	}

}