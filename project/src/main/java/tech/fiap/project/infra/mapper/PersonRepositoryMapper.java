package tech.fiap.project.infra.mapper;

import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.infra.entity.PersonEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonRepositoryMapper {

	public static List<Person> toDomain(List<PersonEntity> persons) {
		return persons.stream().map(PersonRepositoryMapper::toDomain).collect(Collectors.toList());
	}

	public static Person toDomain(PersonEntity person) {
		return new Person(person.getId(), person.getEmail(), person.getPassword(),
				DocumentRepositoryMapper.toDomain(person.getDocuments()));
	}

	public static Optional<Person> toDomain(Optional<PersonEntity> person) {
		return person.map(PersonRepositoryMapper::toDomain);
	}

	public static List<PersonEntity> toEntity(List<Person> people) {
		return people.stream().map(PersonRepositoryMapper::toEntity).collect(Collectors.toList());
	}

	public static PersonEntity toEntity(Person person) {
		if (person == null) {
			return null;
		}
		PersonEntity personEntity = new PersonEntity();
		personEntity.setId(person.getId());
		personEntity.setEmail(person.getEmail());
		personEntity.setPassword(person.getPassword());
		personEntity.setDocuments(DocumentRepositoryMapper.toEntity(person.getDocument()));
		personEntity.setRole(person.getRole());

		return personEntity;
	}

}
