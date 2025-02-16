package tech.fiap.project.infra.dataprovider;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.infra.entity.PersonEntity;
import tech.fiap.project.infra.exception.PersonAlreadyExistsException;
import tech.fiap.project.infra.mapper.PersonRepositoryMapper;
import tech.fiap.project.infra.repository.DocumentRepository;
import tech.fiap.project.infra.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonDataProviderImpl implements PersonDataProvider {

	private PersonRepository personRepository;

	private DocumentRepository documentRepository;

	@Override
	public List<Person> retrieveAll() {
		return PersonRepositoryMapper.toDomain(personRepository.findAll());
	}

	@Override
	public Optional<Person> retrieveByCpf(String cpf) {
		Optional<PersonEntity> personEntity = personRepository.findByDocuments_TypeAndDocuments_Value(DocumentType.CPF,
				cpf);
		return personEntity.map(PersonRepositoryMapper::toDomain);
	}

	@Override
	public Optional<Person> retrieveById(Long id) {
		Optional<PersonEntity> byId = personRepository.findById(id);
		return byId.map(PersonRepositoryMapper::toDomain);
	}

	private boolean documentExists(Person person) {
		List<Document> documents = person.getDocument();
		if (documents == null || documents.isEmpty()) {
			return false;
		}
		return documents.stream().findFirst().map(doc -> {
			String value = doc.getValue();
			if (value == null) {
				return false;
			}
			return documentRepository.findByTypeAndValue(doc.getType(), value).isPresent();
		}).orElse(false);
	}

	@Override
	public Person save(Person person) {
		if (documentExists(person)) {
			String documentValue = person.getDocument().stream().findFirst().orElseThrow().getValue();
			throw new PersonAlreadyExistsException(documentValue);
		}
		PersonEntity personEntity = PersonRepositoryMapper.toEntity(person);
		PersonEntity savedEntity = personRepository.save(personEntity);
		return PersonRepositoryMapper.toDomain(savedEntity);
	}

	@Override
	public void delete(Person person) {
		personRepository.delete(PersonRepositoryMapper.toEntity(person));
	}

	@Override
	public Person update(Person person) {
		PersonEntity personEntity = PersonRepositoryMapper.toEntity(person);
		PersonEntity savedEntity = personRepository.save(personEntity);
		return PersonRepositoryMapper.toDomain(savedEntity);
	}

	@Override
	public void delete(Long id) {
		personRepository.deleteById(id);
	}

}
