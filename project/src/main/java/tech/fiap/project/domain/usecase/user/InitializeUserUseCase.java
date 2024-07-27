package tech.fiap.project.domain.usecase.user;

import tech.fiap.project.domain.entity.Order;

public interface InitializeUserUseCase {

	void execute(Order order);

}
