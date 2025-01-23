package tech.fiap.project.domain.usecase.person;

import tech.fiap.project.app.dto.OrderRequestDTO;

public interface InitializePersonUseCase {

	void execute(OrderRequestDTO order);

}
