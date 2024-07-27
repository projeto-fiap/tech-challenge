package tech.fiap.project.domain.usecase.impl.user;

import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.usecase.user.RetrieveUserUseCase;
import tech.fiap.project.domain.dataprovider.UserDataProvider;

import java.util.List;
import java.util.Optional;

public class RetrieveUserUseCaseImpl implements RetrieveUserUseCase {

	private final UserDataProvider userDataProvider;

	public RetrieveUserUseCaseImpl(UserDataProvider userDataProvider) {
		this.userDataProvider = userDataProvider;
	}

	public Optional<User> findByEmail(String email) {
		return userDataProvider.retrieveByEmail(email);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userDataProvider.retrieveById(id);
	}

	@Override
	public List<User> findAll() {
		return userDataProvider.retrieveAll();
	}

	public Optional<User> findById(String email) {
		return userDataProvider.retrieveByEmail(email);
	}

}
