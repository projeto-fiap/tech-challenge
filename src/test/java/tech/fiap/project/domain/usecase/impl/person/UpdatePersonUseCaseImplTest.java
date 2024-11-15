package tech.fiap.project.domain.usecase.impl.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.infra.exception.PersonNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdatePersonUseCaseImplTest {

	@Mock
	private PersonDataProvider personDataProvider;

	@InjectMocks
	private UpdatePersonUseCaseImpl updatePersonUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void update_updatesPersonSuccessfully() {
		Long id = 1L;
		PersonDTO personDTO = new PersonDTO();
		personDTO.setEmail("test@example.com");
		personDTO.setName("Test Name");

		Person person = new Person();
		person.setId(id);
		person.setEmail("old@example.com");
		person.setName("Old Name");

		when(personDataProvider.retrieveById(id)).thenReturn(Optional.of(person));
		when(personDataProvider.update(person)).thenReturn(person);

		Person updatedPerson = updatePersonUseCaseImpl.update(id, personDTO);

		assertEquals(personDTO.getEmail(), updatedPerson.getEmail());
		assertEquals(personDTO.getName(), updatedPerson.getName());
		verify(personDataProvider).retrieveById(id);
		verify(personDataProvider).update(person);
	}

}