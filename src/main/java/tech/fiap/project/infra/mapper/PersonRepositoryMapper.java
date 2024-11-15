// PersonRepositoryMapper.java
package tech.fiap.project.infra.mapper;

import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.infra.entity.DocumentEntity;
import tech.fiap.project.infra.entity.PersonEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonRepositoryMapper {

	private PersonRepositoryMapper() {
	}

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
		List<Document> documents = person.getDocument();
		if (documents == null) {
			documents = new ArrayList<>();
		}
		personEntity.setDocuments(documents.stream().map(document -> {
			DocumentEntity documentEntity = new DocumentEntity();
			documentEntity.setType(document.getType());
			documentEntity.setValue(document.getValue());
			documentEntity.setPerson(personEntity);
			return documentEntity;
		}).toList());
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
		List<DocumentEntity> documents = personEntity.getDocuments();
		if (documents == null) {
			documents = new ArrayList<>();
		}
		person.setDocument(documents.stream()
				.map(documentEntity -> new Document(documentEntity.getType(), documentEntity.getValue())).toList());
		return person;
	}

	public static List<Person> toDomain(List<PersonEntity> personEntity) {
		return personEntity.stream().map(PersonRepositoryMapper::toDomain).toList();
	}

	public static Optional<Person> toDomain(Optional<PersonEntity> personEntity) {
		return personEntity.map(PersonRepositoryMapper::toDomain);
	}

}