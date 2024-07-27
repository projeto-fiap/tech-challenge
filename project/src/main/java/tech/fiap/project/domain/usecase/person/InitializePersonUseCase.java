package tech.fiap.project.domain.usecase.person;

import tech.fiap.project.domain.entity.Order;

public interface InitializePersonUseCase {

	void execute(Order order);

}
