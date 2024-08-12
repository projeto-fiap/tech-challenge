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
		personDTO.setRole(person.getRole());
		personDTO.setName(person.getName());
		return personDTO;
	}

	public static Person toDomain(PersonDTO personDTO) {
		return new Person(personDTO.getName(), personDTO.getEmail(), personDTO.getPassword(), personDTO.getRole(),
				DocumentMapper.toDomain(personDTO.getDocument()));
	}

}
