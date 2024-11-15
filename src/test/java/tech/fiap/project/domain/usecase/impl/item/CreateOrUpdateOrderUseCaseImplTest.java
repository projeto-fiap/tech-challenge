package tech.fiap.project.domain.usecase.impl.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.dataprovider.OrderDataProvider;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.impl.order.CalculateTotalOrderUseCaseImpl;
import tech.fiap.project.domain.usecase.item.InitializeItemUseCase;
import tech.fiap.project.domain.usecase.order.CalculateTotalOrderUseCase;
import tech.fiap.project.domain.usecase.person.InitializePersonUseCase;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreateOrUpdateOrderUseCaseImplTest {

	@Mock
	private OrderDataProvider orderDataProvider;

	@Mock
	private InitializePersonUseCase initializePersonUseCase;

	@Mock
	private InitializeItemUseCase initializeItemUseCase;

	private final CalculateTotalOrderUseCase calculateTotalOrderUseCase = new CalculateTotalOrderUseCaseImpl();

	private CreateOrUpdateOrderUseCaseImpl createOrUpdateOrderUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		createOrUpdateOrderUseCaseImpl = new CreateOrUpdateOrderUseCaseImpl(orderDataProvider, initializePersonUseCase,
				initializeItemUseCase, calculateTotalOrderUseCase);
	}

	@Test
	void execute_createOrder() {
		Item item1 = new Item(1L, "Hamburguer", BigDecimal.valueOf(45.5), BigDecimal.valueOf(1), "unit",
				ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne", "https://www.google.com");
		Item item2 = new Item(1L, "Hamburguer", BigDecimal.valueOf(45.5), BigDecimal.valueOf(1), "unit",
				ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne", "https://www.google.com");
		List<Item> items = Arrays.asList(item1, item2);
		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now().plusDays(1), items,
				new ArrayList<>(), null, null, BigDecimal.TEN);
		when(orderDataProvider.retrieveAll(order)).thenReturn(Optional.empty());
		when(orderDataProvider.create(order)).thenReturn(order);

		Order result = createOrUpdateOrderUseCaseImpl.execute(order);

		assertEquals(OrderStatus.PENDING, result.getStatus());
		assertEquals(Duration.ZERO, result.getAwaitingTime());
		assertEquals(BigDecimal.valueOf(91.0), result.getTotalPrice());
		verify(orderDataProvider, times(1)).create(order);
	}

	@Test
	void execute_updateOrder() {
		Item item1 = new Item(1L, "Hamburguer", BigDecimal.valueOf(45.5), BigDecimal.valueOf(1), "unit",
				ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne", "https://www.google.com");
		Item item2 = new Item(1L, "Hamburguer", BigDecimal.valueOf(45.5), BigDecimal.valueOf(1), "unit",
				ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne", "https://www.google.com");
		Item item3 = new Item(2L, "Refrigerante", BigDecimal.valueOf(5.5), BigDecimal.valueOf(1), "unit",
				ItemCategory.DRINK, new ArrayList<>(), "Refrigerante de cola", "https://www.google.com");
		List<Item> oldItems = Arrays.asList(item1, item2);
		List<Item> newItems = Arrays.asList(item1, item2, item3);
		Order oldOrder = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now().plusDays(1),
				oldItems, new ArrayList<>(), null, null, BigDecimal.TEN);
		Order newOrder = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now().plusDays(1),
				newItems, new ArrayList<>(), null, null, BigDecimal.TEN);
		when(orderDataProvider.retrieveAll(oldOrder)).thenReturn(Optional.of(oldOrder));
		when(orderDataProvider.create(newOrder)).thenReturn(newOrder);

		Order result = createOrUpdateOrderUseCaseImpl.execute(newOrder);

		assertEquals(OrderStatus.PENDING, result.getStatus());
		assertEquals(BigDecimal.valueOf(96.5), result.getTotalPrice());
		verify(orderDataProvider, times(1)).create(newOrder);
	}

}