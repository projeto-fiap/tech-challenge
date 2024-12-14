package tech.fiap.project.domain.usecase.impl.person;

import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.RetrievePersonUseCase;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;

import java.util.List;
import java.util.Optional;

public class RetrievePersonUseCaseImpl implements RetrievePersonUseCase {

	private final PersonDataProvider personDataProvider;

	public RetrievePersonUseCaseImpl(PersonDataProvider personDataProvider) {
		this.personDataProvider = personDataProvider;
	}

	@Override
	public Optional<Person> findByCpf(String cpf) {
		return personDataProvider.retrieveByCpf(cpf);
	}

	@Override
	public Optional<Person> findById(Long id) {
		return personDataProvider.retrieveById(id);
	}

	@Override
	public List<Person> findAll() {
		return personDataProvider.retrieveAll();
	}

}
