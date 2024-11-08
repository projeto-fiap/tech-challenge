package tech.fiap.project.domain.usecase.impl.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateTotalOrderUseCaseImplTest {

	private CalculateTotalOrderUseCaseImpl calculateTotalOrderUseCaseImpl;

	@BeforeEach
	void setUp() {
		calculateTotalOrderUseCaseImpl = new CalculateTotalOrderUseCaseImpl();
	}

	@Test
	void execute_calculatesTotalOrderSuccessfully() {
		Item item1 = new Item(1L, "Item 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(2), "unit", ItemCategory.FOOD,
				null, "description", "imageUrl");
		Item item2 = new Item(2L, "Item 2", BigDecimal.valueOf(5.00), BigDecimal.valueOf(3), "unit", ItemCategory.DRINK,
				null, "description", "imageUrl");
		List<Item> items = Arrays.asList(item1, item2);

		BigDecimal result = calculateTotalOrderUseCaseImpl.execute(items);

		assertEquals(BigDecimal.valueOf(35.00), result);
	}

	@Test
	void execute_calculatesTotalOrderWithIngredientsSuccessfully() {
		Item ingredient1 = new Item(3L, "Ingredient 1", BigDecimal.valueOf(2.00), BigDecimal.valueOf(1), "unit",
				ItemCategory.INGREDIENT, null, "description", "imageUrl");
		Item ingredient2 = new Item(4L, "Ingredient 2", BigDecimal.valueOf(3.00), BigDecimal.valueOf(1), "unit",
				ItemCategory.INGREDIENT, null, "description", "imageUrl");
		Item item = new Item(1L, "Item with Ingredients", BigDecimal.valueOf(10.00), BigDecimal.valueOf(1), "unit",
				ItemCategory.FOOD, Arrays.asList(ingredient1, ingredient2), "description", "imageUrl");
		List<Item> items = Arrays.asList(item);

		BigDecimal result = calculateTotalOrderUseCaseImpl.execute(items);

		assertEquals(BigDecimal.valueOf(15.00), result);
	}

}