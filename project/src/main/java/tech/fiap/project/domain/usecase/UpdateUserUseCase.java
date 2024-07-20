package tech.fiap.project.domain.usecase;

import tech.fiap.project.domain.entity.User;

public interface UpdateUserUseCase {

	User update(String email);

}
