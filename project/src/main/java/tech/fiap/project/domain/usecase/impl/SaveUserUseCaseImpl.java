package tech.fiap.project.domain.usecase.impl;

import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.usecase.RetrieveUserUseCase;
import tech.fiap.project.domain.usecase.SaveUserUseCase;
import tech.fiap.project.domain.usecase.UserDataProvider;

import java.util.List;
import java.util.Optional;

public class SaveUserUseCaseImpl implements SaveUserUseCase {

	private final UserDataProvider userDataProvider;

	public SaveUserUseCaseImpl(UserDataProvider userDataProvider) {
		this.userDataProvider = userDataProvider;
	}

	public User save(User user) {
		return userDataProvider.save(user);
	}

}
