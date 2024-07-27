package tech.fiap.project.domain.usecase.impl.user;

import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.usecase.user.SaveUserUseCase;
import tech.fiap.project.domain.dataprovider.UserDataProvider;

public class SaveUserUseCaseImpl implements SaveUserUseCase {

	private final UserDataProvider userDataProvider;

	public SaveUserUseCaseImpl(UserDataProvider userDataProvider) {
		this.userDataProvider = userDataProvider;
	}

	public User save(User user) {
		return userDataProvider.save(user);
	}

}
