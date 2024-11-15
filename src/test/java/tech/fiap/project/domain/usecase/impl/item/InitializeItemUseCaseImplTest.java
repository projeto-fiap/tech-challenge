package tech.fiap.project.domain.usecase.impl.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.dataprovider.ItemDataProvider;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class InitializeItemUseCaseImplTest {

	@Mock
	private ItemDataProvider itemDataProvider;

	@InjectMocks
	private InitializeItemUseCaseImpl initializeItemUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_initializesItemsSuccessfully() {
		Item item1Empty = new Item(1L, null, null, null, null, null, null, null, null);
		Item item2Empty = new Item(2L, null, null, null, null, null, null, null, null);
		Item item1 = new Item(1L, "Hamburguer", BigDecimal.valueOf(45.5), BigDecimal.valueOf(1), "unit",
				ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne", "https://www.google.com");
		Item item2 = new Item(2L, "Refrigerante", BigDecimal.valueOf(5.5), BigDecimal.valueOf(1), "unit",
				ItemCategory.DRINK, new ArrayList<>(), "Refrigerante de cola", "https://www.google.com");

		Order order = new Order(1L, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now().plusDays(1),
				Arrays.asList(item1Empty, item2Empty), new ArrayList<>(), null, null, BigDecimal.TEN);
		when(itemDataProvider.retrieveById(1L)).thenReturn(Optional.of(item1));
		when(itemDataProvider.retrieveById(2L)).thenReturn(Optional.of(item2));
		initializeItemUseCaseImpl.execute(order);

		order.getItems().stream().forEach(item -> assertNotNull(item.getName()));
	}

}