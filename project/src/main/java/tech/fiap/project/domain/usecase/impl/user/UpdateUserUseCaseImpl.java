package tech.fiap.project.domain.usecase.impl.user;

import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.usecase.user.UpdateUserUseCase;
import tech.fiap.project.domain.dataprovider.UserDataProvider;
import tech.fiap.project.infra.exception.UserNotFoundException;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

	private final UserDataProvider userDataProvider;

	public UpdateUserUseCaseImpl(UserDataProvider userDataProvider) {
		this.userDataProvider = userDataProvider;
	}

	@Override
	public User update(String email) {
		return userDataProvider.retrieveByEmail(email).map(user -> {
			user.setEmail(email);
			return userDataProvider.save(user);
		}).orElseThrow(() -> new UserNotFoundException(email));
	}

}
