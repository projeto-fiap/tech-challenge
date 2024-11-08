package tech.fiap.project.domain.usecase.impl.order;

import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.order.CalculateTotalOrderUseCase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CalculateTotalOrderUseCaseImpl implements CalculateTotalOrderUseCase {

	public BigDecimal execute(List<Item> items) {
		AtomicReference<List<Item>> newItens = new AtomicReference<>(new ArrayList<>());
		items.forEach(item -> {
			if (item.getIngredients() != null) {
				newItens.getAndAccumulate(buildListIngredients(item.getIngredients()), (list, ingredients) -> {
					list.addAll(ingredients);
					return list;
				});
			}
			newItens.get().add(item);
		});
		return calculate(newItens.get());
	}

	private static BigDecimal calculate(List<Item> items) {
		return items.stream().map(item -> item.getPrice().multiply(item.getQuantity())).reduce(BigDecimal.ZERO,
				BigDecimal::add);
	}

	private List<Item> buildListIngredients(List<Item> items) {
		List<Item> ingredients = new ArrayList<>();
		items.forEach(item -> {
			if (item.getIngredients() != null && !item.getIngredients().isEmpty()) {
				buildListIngredients(item.getIngredients());
			}
			else {
				ingredients.add(item);
			}
		});
		return ingredients;
	}

}
