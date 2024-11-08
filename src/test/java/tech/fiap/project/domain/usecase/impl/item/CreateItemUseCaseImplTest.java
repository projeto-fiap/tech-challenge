package tech.fiap.project.domain.usecase.impl.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.dataprovider.ItemDataProvider;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateItemUseCaseImplTest {

	@Mock
	private ItemDataProvider itemDataProvider;

	@InjectMocks
	private CreateItemUseCaseImpl createItemUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute() {
		Item item1 = new Item(1L, "Hamburguer", BigDecimal.valueOf(45.5), BigDecimal.valueOf(100.0), "gramas",
				ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne", "https://www.google.com");
		Item item2 = new Item(2L, "Refrigerante", BigDecimal.valueOf(5.5), BigDecimal.valueOf(100.0), "ml",
				ItemCategory.DRINK, new ArrayList<>(), "Refrigerante de cola", "https://www.google.com");
		List<Item> items = Arrays.asList(item1, item2);

		Mockito.when(itemDataProvider.saveAll(items)).thenReturn(items);

		List<Item> result = createItemUseCaseImpl.execute(items);

		assertEquals(items, result);
	}

}