package tech.fiap.project.domain.usecase.impl.user;

import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.UpdatePersonUseCase;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.infra.exception.PersonNotFoundException;

public class UpdatePersonUseCaseImpl implements UpdatePersonUseCase {

	private final PersonDataProvider personDataProvider;

	public UpdatePersonUseCaseImpl(PersonDataProvider personDataProvider) {
		this.personDataProvider = personDataProvider;
	}

	@Override
	public Person update(String email) {
		return personDataProvider.retrieveByEmail(email).map(user -> {
			user.setEmail(email);
			return personDataProvider.save(user);
		}).orElseThrow(() -> new PersonNotFoundException(email));
	}

}
