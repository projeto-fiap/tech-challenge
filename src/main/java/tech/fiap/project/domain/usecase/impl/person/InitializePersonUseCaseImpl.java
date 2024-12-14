package tech.fiap.project.domain.usecase.impl.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.InitializePersonUseCase;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.infra.exception.InvalidPersonException;
import tech.fiap.project.infra.exception.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

public class InitializePersonUseCaseImpl implements InitializePersonUseCase {

	private final PersonDataProvider personDataProvider;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public InitializePersonUseCaseImpl(PersonDataProvider personDataProvider) {
		this.personDataProvider = personDataProvider;
	}

	@Override
	public void execute(Order order) {
		Person person = getPerson(order);
		order.setPerson(person);
	}

	private Person getPerson(Order order) {
		Person person = order.getPerson();
		if (person != null) {
			validatePerson(person);
			Optional<Person> personSaved = personDataProvider.retrieveByCpf(person.getDocument().get(0).getValue());
			if (personSaved.isPresent()) {
				return personSaved.get();
			}
			else {
				throw new PersonNotFoundException(person.getDocument().get(0).getValue());
			}
		}
		return null;
	}

	private void validatePerson(Person person) {
		if (person != null) {
			List<Document> documents = person.getDocument();
			if (documents != null && !documents.isEmpty()) {
				Document document = documents.get(0);
				if (document.getType() != null && document.getValue() != null) {
					log.debug("Person with valid document when creating an order");
				}
				else {
					throw new InvalidPersonException(person);
				}
			}
			else {
				throw new InvalidPersonException(person);
			}
		}
	}

}