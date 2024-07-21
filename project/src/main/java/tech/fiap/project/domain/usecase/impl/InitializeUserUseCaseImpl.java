package tech.fiap.project.domain.usecase.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.usecase.InitializeUserUseCase;
import tech.fiap.project.domain.usecase.UserDataProvider;
import tech.fiap.project.infra.exception.InvalidUserException;
import tech.fiap.project.infra.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public class InitializeUserUseCaseImpl implements InitializeUserUseCase {

	private final UserDataProvider userDataProvider;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public InitializeUserUseCaseImpl(UserDataProvider userDataProvider) {
		this.userDataProvider = userDataProvider;
	}

	@Override
	public void execute(Order order) {
		User user = getUser(order);
		order.setUser(user);
	}

	private User getUser(Order order) {
		User user = order.getUser();
		validateUser(user);
		Optional<User> userSaved = userDataProvider.retrieveByCPF(user.getDocument().get(0).getValue());
		if (userSaved.isPresent()) {
			return userSaved.get();
		}
		else {
			throw new UserNotFoundException(user.getDocument().get(0).getValue());
		}
	}

	private void validateUser(User user) {
		if (user != null) {
			List<Document> documents = user.getDocument();
			if (!documents.isEmpty()) {
				Document document = documents.get(0);
				if (document.getType() != null && document.getValue() != null) {
					log.debug("User with valid document when creating an order");
				}
				else {
					throw new InvalidUserException(user);
				}
			}
		}
	}

}