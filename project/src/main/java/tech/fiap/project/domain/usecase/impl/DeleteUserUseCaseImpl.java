package tech.fiap.project.domain.usecase.impl;

import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.usecase.DeleteUserUseCase;
import tech.fiap.project.domain.usecase.UserDataProvider;
import tech.fiap.project.infra.exception.UserNotFoundException;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

	private final UserDataProvider userDataProvider;

	public DeleteUserUseCaseImpl(UserDataProvider userDataProvider) {
		this.userDataProvider = userDataProvider;
	}

	@Override
	public void delete(String email) {
		User user = userDataProvider.retrieveByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
		userDataProvider.delete(user);
	}

}
