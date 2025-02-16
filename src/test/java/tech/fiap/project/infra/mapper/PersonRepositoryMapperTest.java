package tech.fiap.project.infra.mapper;

import org.junit.jupiter.api.Test;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.entity.Role;
import tech.fiap.project.infra.entity.PersonEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryMapperTest {

	@Test
	void toEntity_shouldReturnNullWhenPersonIsNull() {
		assertNull(PersonRepositoryMapper.toEntity(null));
	}

	@Test
	void toEntity_shouldMapPersonToPersonEntity() {
		Person person = new Person(1L, "John Doe", "john@example.com", "password", Role.ADMIN,
				List.of(new Document(DocumentType.CPF, "123456789")));
		PersonEntity personEntity = PersonRepositoryMapper.toEntity(person);

		assertNotNull(personEntity);
		assertEquals(person.getId(), personEntity.getId());
		assertEquals(person.getEmail(), personEntity.getEmail());
		assertEquals(person.getPassword(), personEntity.getPassword());
		assertEquals(person.getRole(), personEntity.getRole());
		assertEquals(person.getName(), personEntity.getName());
		assertEquals(1, personEntity.getDocuments().size());
		assertEquals("123456789", personEntity.getDocuments().get(0).getValue());
	}

	@Test
	void toDomain_shouldReturnEmptyOptionalWhenPersonEntityIsEmpty() {
		Optional<Person> person = PersonRepositoryMapper.toDomain(Optional.empty());
		assertTrue(person.isEmpty());
	}

}
