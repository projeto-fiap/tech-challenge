package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.domain.entity.Person;

public class PersonMapper {

	public static PersonDTO toDTO(Person person) {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(person.getId());
		personDTO.setEmail(person.getEmail());
		personDTO.setPassword(person.getPassword());
		personDTO.setDocument(DocumentMapper.toDTO(person.getDocument()));
		return personDTO;
	}

	public static Person toDomain(PersonDTO person) {
		return new Person(person.getId(), person.getEmail(), person.getPassword(), DocumentMapper.toDomain(person.getDocument()));
	}

}
