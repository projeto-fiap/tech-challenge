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
	public void delete(Long id) {
		personDataProvider.delete(id);
	}

	@Override
	public void delete(String cpf) {
		Optional<Person> person = personDataProvider.retrieveByCpf(cpf);
		if (person.isPresent()) {
			personDataProvider.delete(person.get());
		}
		else {
			throw new PersonNotFoundException("Person with CPF " + cpf + " not found");
		}
	}

}
