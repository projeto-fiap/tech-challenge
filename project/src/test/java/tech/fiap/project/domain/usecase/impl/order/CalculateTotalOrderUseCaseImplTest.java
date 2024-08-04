package tech.fiap.project.domain.usecase.impl.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculateTotalOrderUseCaseImplTest {

	CalculateTotalOrderUseCaseImpl calculateTotalOrderUseCase = new CalculateTotalOrderUseCaseImpl();

	@Test
	void execute() {
		List<Item> items = createHamburgerIngredients();
		BigDecimal value = calculateTotalOrderUseCase.execute(items);
		Assertions.assertEquals(BigDecimal.valueOf(131.0), value);
	}

	private List<Item> createHamburgerIngredients() {
		List<Item> ingredientsBurguer = new ArrayList<>();
		Item alface = new Item(null, "Alface", BigDecimal.TEN, BigDecimal.valueOf(0.0), "grama",
				ItemCategory.INGREDIENT, null, null, null);
		ingredientsBurguer.add(alface);
		Item queijo = new Item(null, "Queijo", BigDecimal.ONE, BigDecimal.valueOf(30.5), "grama",
				ItemCategory.ADDITIONAL_INGREDIENT, null, null, null);
		ingredientsBurguer.add(queijo);

		List<Item> ingredients = new ArrayList<>();
		Item burguer = new Item(null, "Hamburguer", BigDecimal.ONE, BigDecimal.valueOf(100.5), "grama",
				ItemCategory.INGREDIENT, ingredientsBurguer, null, null);
		ingredients.add(burguer);
		return ingredients;
	}

}