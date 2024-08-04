package tech.fiap.project.domain.usecase.impl.person;

import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.DeletePersonUseCase;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.infra.exception.PersonNotFoundException;

import java.util.Optional;

public class DeletePersonUseCaseImpl implements DeletePersonUseCase {

	private final PersonDataProvider personDataProvider;

	public DeletePersonUseCaseImpl(PersonDataProvider personDataProvider) {
		this.personDataProvider = personDataProvider;
	}

	@Override
	public void delete(String email) {
		Person person = personDataProvider.retrieveByEmail(email)
				.orElseThrow(() -> new PersonNotFoundException(Optional.of(email)));
		personDataProvider.delete(person);
	}

	@Override
	public void delete(Long id) {
		personDataProvider.delete(id);
	}

}
