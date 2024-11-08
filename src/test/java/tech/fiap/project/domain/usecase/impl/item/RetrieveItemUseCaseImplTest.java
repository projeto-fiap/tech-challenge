package tech.fiap.project.domain.usecase.impl.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.dataprovider.ItemDataProvider;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class RetrieveItemUseCaseImplTest {

	@Mock
	private ItemDataProvider itemDataProvider;

	@InjectMocks
	private RetrieveItemUseCaseImpl retrieveItemUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findAll_returnsAllItems() {
		Item item1 = new Item(1L, "Hamburguer", BigDecimal.valueOf(45.5), BigDecimal.valueOf(1), "unit",
				ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne", "https://www.google.com");
		Item item2 = new Item(2L, "Refrigerante", BigDecimal.valueOf(5.5), BigDecimal.valueOf(1), "unit",
				ItemCategory.DRINK, new ArrayList<>(), "Refrigerante de cola", "https://www.google.com");
		List<Item> items = Arrays.asList(item1, item2);

		when(itemDataProvider.retrieveAll()).thenReturn(items);

		List<Item> result = retrieveItemUseCaseImpl.findAll();

		assertEquals(2, result.size());
		assertEquals(items, result);
	}

	@Test
	void findById_returnsItemWhenFound() {
		Item item = new Item(1L, "Hamburguer", BigDecimal.valueOf(45.5), BigDecimal.valueOf(1), "unit",
				ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne", "https://www.google.com");

		when(itemDataProvider.retrieveById(1L)).thenReturn(Optional.of(item));

		Optional<Item> result = retrieveItemUseCaseImpl.findById(1L);

		assertTrue(result.isPresent());
		assertEquals(item, result.get());
	}

	@Test
	void findById_returnsEmptyWhenNotFound() {
		when(itemDataProvider.retrieveById(1L)).thenReturn(Optional.empty());

		Optional<Item> result = retrieveItemUseCaseImpl.findById(1L);

		assertTrue(result.isEmpty());
	}

	@Test
	void findByCategory_returnsItemsByCategory() {
		Item item1 = new Item(1L, "Hamburguer", BigDecimal.valueOf(45.5), BigDecimal.valueOf(1), "unit",
				ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne", "https://www.google.com");
		Item item2 = new Item(2L, "Refrigerante", BigDecimal.valueOf(5.5), BigDecimal.valueOf(1), "unit",
				ItemCategory.DRINK, new ArrayList<>(), "Refrigerante de cola", "https://www.google.com");
		List<Item> items = Arrays.asList(item1, item2);

		when(itemDataProvider.retrieveByCategory(ItemCategory.FOOD)).thenReturn(items);

		List<Item> result = retrieveItemUseCaseImpl.findByCategory(ItemCategory.FOOD);

		assertEquals(2, result.size());
		assertEquals(items, result);
	}

}