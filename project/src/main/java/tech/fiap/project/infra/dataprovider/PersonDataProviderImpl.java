package tech.fiap.project.infra.dataprovider;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.infra.entity.PersonEntity;
import tech.fiap.project.infra.exception.PersonAlreadyExistsException;
import tech.fiap.project.infra.mapper.PersonRepositoryMapper;
import tech.fiap.project.infra.entity.QDocumentEntity;
import tech.fiap.project.infra.entity.QPersonEntity;
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
	public Optional<Person> retrieveByEmail(String email) {
		Optional<PersonEntity> byEmail = personRepository.findByEmail(email);
		return byEmail.map(PersonRepositoryMapper::toDomain);
	}

	@Override
	public Optional<Person> retrieveByCPF(String cpf) {
		QPersonEntity personEntity = QPersonEntity.personEntity;
		QDocumentEntity anyDocument = personEntity.documents.any();
		BooleanExpression predicate = anyDocument.type.eq(DocumentType.CPF).and(anyDocument.value.eq(cpf));
		return PersonRepositoryMapper.toDomain(personRepository.findOne(predicate));
	}

	@Override
	public Optional<Person> retrieveById(Long id) {
		Optional<PersonEntity> byId = personRepository.findById(id);
		return byId.map(PersonRepositoryMapper::toDomain);
	}

	private boolean documentExists(Person person) {
		return person.getDocument().stream().findFirst()
				.map(doc -> !documentRepository.findByTypeAndValue(doc.getType(), doc.getValue()).isEmpty())
				.orElse(false);
	}

	@Override
	public Person save(Person person) {
		if (documentExists(person)) {
			String documentValue = person.getDocument().stream().findFirst().get().getValue();
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
	public void delete(Long id) {
		personRepository.deleteById(id);
	}

}
