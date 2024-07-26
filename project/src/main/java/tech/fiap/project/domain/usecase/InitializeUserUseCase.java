package tech.fiap.project.domain.usecase;

import tech.fiap.project.domain.entity.Order;

public interface InitializeUserUseCase {

	void execute(Order order);

}
