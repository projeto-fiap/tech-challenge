package tech.fiap.project.domain.usecase.impl.person;

import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.SavePersonUseCase;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;

public class SavePersonUseCaseImpl implements SavePersonUseCase {

	private final PersonDataProvider personDataProvider;

	public SavePersonUseCaseImpl(PersonDataProvider personDataProvider) {
		this.personDataProvider = personDataProvider;
	}

	public Person save(Person person) {
		return personDataProvider.save(person);
	}

}
