package tech.fiap.project.domain.usecase.order;

import tech.fiap.project.domain.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public interface CalculateTotalOrderUseCase {

	BigDecimal execute(List<Item> items);

}
