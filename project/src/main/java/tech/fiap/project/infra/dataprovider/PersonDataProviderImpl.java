package tech.fiap.project.infra.dataprovider;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.infra.entity.PersonEntity;
import tech.fiap.project.infra.mapper.PersonRepositoryMapper;
import tech.fiap.project.infra.entity.QDocumentEntity;
import tech.fiap.project.infra.entity.QUserEntity;
import tech.fiap.project.infra.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonDataProviderImpl implements PersonDataProvider {

	private PersonRepository personRepository;

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
		QUserEntity userEntity = QUserEntity.userEntity;
		QDocumentEntity anyDocument = userEntity.documents.any();
		BooleanExpression predicate = anyDocument.type.eq(DocumentType.CPF).and(anyDocument.value.eq(cpf));
		return PersonRepositoryMapper.toDomain(personRepository.findOne(predicate));
	}

	@Override
	public Optional<Person> retrieveById(Long id) {
		Optional<PersonEntity> byId = personRepository.findById(id);
		return byId.map(PersonRepositoryMapper::toDomain);
	}

	@Override
	public Person save(Person person) {
		return PersonRepositoryMapper.toDomain(personRepository.save(PersonRepositoryMapper.toEntity(person)));
	}

	@Override
	public void delete(Person person) {
		personRepository.delete(PersonRepositoryMapper.toEntity(person));
	}

}
