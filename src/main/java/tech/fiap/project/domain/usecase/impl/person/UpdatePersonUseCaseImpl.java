package tech.fiap.project.domain.usecase.impl.person;

import tech.fiap.project.app.dto.PersonDTO;
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
	public Person update(Long id, PersonDTO personDTO) {
		return personDataProvider.retrieveById(id).map(person -> {
			person.setEmail(personDTO.getEmail());
			person.setName(personDTO.getName());
			return personDataProvider.update(person);
		}).orElseThrow(() -> new PersonNotFoundException(personDTO.getEmail()));
	}

}
