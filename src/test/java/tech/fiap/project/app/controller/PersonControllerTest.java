package tech.fiap.project.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.app.service.person.DeletePersonService;
import tech.fiap.project.app.service.person.RetrievePersonService;
import tech.fiap.project.app.service.person.SavePersonService;
import tech.fiap.project.app.service.person.UpdatePersonService;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.entity.Role;
import tech.fiap.project.infra.exception.PersonNotFoundException;
import tech.fiap.project.infra.exception.UnauthorizedException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class PersonControllerTest {

	@Mock
	private RetrievePersonService retrievePersonService;

	@Mock
	private UpdatePersonService updatePersonService;

	@Mock
	private SavePersonService savePersonService;

	@Mock
	private DeletePersonService deletePersonService;

	@InjectMocks
	private PersonController personController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getPersonByEmail_shouldReturnPersonDTO_whenFound() {
		String email = "test@example.com";
		PersonDTO personDTO = new PersonDTO();
		when(retrievePersonService.findByEmail(email)).thenReturn(Optional.of(personDTO));

		ResponseEntity<PersonDTO> response = personController.getPerson(email);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(personDTO, response.getBody());
	}

	@Test
	void getPersonByEmail_shouldReturnNotFound_whenNotFound() {
		String email = "test@example.com";
		when(retrievePersonService.findByEmail(email)).thenReturn(Optional.empty());

		ResponseEntity<PersonDTO> response = personController.getPerson(email);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void getPersonById_shouldReturnPersonDTO_whenFound() {
		Long id = 1L;
		PersonDTO personDTO = new PersonDTO();
		when(retrievePersonService.findById(id)).thenReturn(Optional.of(personDTO));

		ResponseEntity<PersonDTO> response = personController.getPerson(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(personDTO, response.getBody());
	}

	@Test
	void getPersonById_shouldReturnNotFound_whenNotFound() {
		Long id = 1L;
		when(retrievePersonService.findById(id)).thenReturn(Optional.empty());

		ResponseEntity<PersonDTO> response = personController.getPerson(id);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void getPersons_shouldReturnListOfPersonDTO() {
		List<PersonDTO> persons = List.of(new PersonDTO());
		when(retrievePersonService.findAll()).thenReturn(persons);

		ResponseEntity<List<PersonDTO>> response = personController.getPersons();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(persons, response.getBody());
	}

	@Test
	void savePerson_shouldReturnPersonDTO_whenRoleIsUser() {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setRole(Role.USER);
		Person person = new Person();
		person.setRole(Role.USER);
		when(savePersonService.save(Mockito.any(Person.class), eq(null))).thenReturn(person);

		ResponseEntity<PersonDTO> response = personController.savePerson(personDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(personDTO, response.getBody());
	}

	@Test
	void savePerson_shouldThrowUnauthorizedException_whenRoleIsNotUser() {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setRole(Role.ADMIN);

		assertThrows(UnauthorizedException.class, () -> personController.savePerson(personDTO));
	}

	@Test
	void savePersonAdmin_shouldReturnPersonDTO() {
		PersonDTO personDTO = new PersonDTO();
		Person person = new Person();
		when(savePersonService.save(any(Person.class), eq(null))).thenReturn(person);

		ResponseEntity<PersonDTO> response = personController.savePersonAdmin(personDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(personDTO, response.getBody());
	}

	@Test
	void updatePerson_shouldReturnPersonDTO_whenSuccessful() {
		Long id = 1L;
		PersonDTO personDTO = new PersonDTO();
		when(updatePersonService.update(personDTO, id)).thenReturn(personDTO);

		ResponseEntity<PersonDTO> response = personController.updatePerson(personDTO, id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(personDTO, response.getBody());
	}

	@Test
	void updatePerson_shouldReturnNotFound_whenPersonNotFound() {
		Long id = 1L;
		PersonDTO personDTO = new PersonDTO();
		when(updatePersonService.update(personDTO, id)).thenThrow(new PersonNotFoundException("id"));

		ResponseEntity<PersonDTO> response = personController.updatePerson(personDTO, id);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void deletePerson_shouldReturnOk_whenSuccessful() {
		PersonDTO personDTO = new PersonDTO();

		ResponseEntity<Void> response = personController.deletePerson(personDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void deletePerson_shouldReturnNotFound_whenPersonNotFound() {
		PersonDTO personDTO = new PersonDTO();
		doThrow(new PersonNotFoundException("id")).when(deletePersonService).delete(personDTO);

		ResponseEntity<Void> response = personController.deletePerson(personDTO);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void deletePersonById_shouldReturnOk_whenSuccessful() {
		Long id = 1L;

		ResponseEntity<Void> response = personController.deletePersonById(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void deletePersonById_shouldReturnNotFound_whenPersonNotFound() {
		Long id = 1L;
		doThrow(new PersonNotFoundException("id")).when(deletePersonService).delete(id);

		ResponseEntity<Void> response = personController.deletePersonById(id);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

}