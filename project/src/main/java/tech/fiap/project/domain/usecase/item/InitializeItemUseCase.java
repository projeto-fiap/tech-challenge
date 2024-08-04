package tech.fiap.project.domain.usecase.item;

import tech.fiap.project.domain.entity.Order;

public interface InitializeItemUseCase {

	void execute(Order order);

}
