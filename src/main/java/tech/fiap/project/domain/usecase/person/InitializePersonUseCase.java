package tech.fiap.project.domain.usecase.person;

import org.apache.coyote.BadRequestException;
import tech.fiap.project.app.dto.OrderRequestDTO;

public interface InitializePersonUseCase {

	void execute(OrderRequestDTO order) throws BadRequestException;

}
