// PersonRepositoryMapper.java
package tech.fiap.project.infra.mapper;

import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.infra.entity.PersonEntity;
import tech.fiap.project.infra.entity.DocumentEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonRepositoryMapper {

	public static PersonEntity toEntity(Person person) {
		if (person == null) {
			return null;
		}
		PersonEntity personEntity = new PersonEntity();
		personEntity.setId(person.getId());
		personEntity.setEmail(person.getEmail());
		personEntity.setPassword(person.getPassword());
		personEntity.setRole(person.getRole());
		personEntity.setName(person.getName());
		personEntity.setDocuments(person.getDocument().stream().map(document -> {
			DocumentEntity documentEntity = new DocumentEntity();
			documentEntity.setType(document.getType());
			documentEntity.setValue(document.getValue());
			documentEntity.setPerson(personEntity);
			return documentEntity;
		}).collect(Collectors.toList()));
		return personEntity;
	}

	public static Person toDomain(PersonEntity personEntity) {
		if (personEntity == null) {
			return null;
		}
		Person person = new Person();
		person.setId(personEntity.getId());
		person.setEmail(personEntity.getEmail());
		person.setName(personEntity.getName());
		person.setPassword(personEntity.getPassword());
		person.setRole(personEntity.getRole());
		person.setDocument(personEntity.getDocuments().stream()
				.map(documentEntity -> new Document(documentEntity.getType(), documentEntity.getValue()))
				.collect(Collectors.toList()));
		return person;
	}

	public static List<Person> toDomain(List<PersonEntity> personEntity) {
		return personEntity.stream().map(PersonRepositoryMapper::toDomain).collect(Collectors.toList());
	}

	public static Optional<Person> toDomain(Optional<PersonEntity> personEntity) {
		return personEntity.map(PersonRepositoryMapper::toDomain);
	}

}