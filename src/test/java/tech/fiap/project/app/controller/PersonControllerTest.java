package tech.fiap.project.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
	void getPersonByCpf_shouldReturnPersonWhenFound() {
		String cpf = "12345678900";
		PersonDTO personDTO = new PersonDTO();
		when(retrievePersonService.findByCpf(cpf)).thenReturn(Optional.of(personDTO));

		ResponseEntity<PersonDTO> response = personController.getPersonByCpf(cpf);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(personDTO, response.getBody());
	}

	@Test
	void getPersonByCpf_shouldReturnNotFoundWhenPersonNotExists() {
		String cpf = "12345678900";
		when(retrievePersonService.findByCpf(cpf)).thenReturn(Optional.empty());

		ResponseEntity<PersonDTO> response = personController.getPersonByCpf(cpf);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void getPersonById_shouldReturnPersonWhenFound() {
		Long id = 1L;
		PersonDTO personDTO = new PersonDTO();
		when(retrievePersonService.findById(id)).thenReturn(Optional.of(personDTO));

		ResponseEntity<PersonDTO> response = personController.getPerson(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(personDTO, response.getBody());
	}

	@Test
	void getPersonById_shouldReturnNotFoundWhenPersonNotExists() {
		Long id = 1L;
		when(retrievePersonService.findById(id)).thenReturn(Optional.empty());

		ResponseEntity<PersonDTO> response = personController.getPerson(id);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void getPersons_shouldReturnListOfPersons() {
		List<PersonDTO> persons = Collections.singletonList(new PersonDTO());
		when(retrievePersonService.findAll()).thenReturn(persons);

		ResponseEntity<List<PersonDTO>> response = personController.getPersons();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(persons, response.getBody());
	}

	@Test
	void savePerson_shouldReturnUnauthorizedWhenRoleIsUser() {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setRole(Role.USER);

		assertThrows(UnauthorizedException.class, () -> personController.savePerson(personDTO));
	}

	@Test
	void savePerson_shouldReturnSavedPersonWhenRoleIsNotUser() throws Exception {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setRole(Role.ADMIN);
		Person person = new Person();
		when(savePersonService.save(any(), any())).thenReturn(person);

		ResponseEntity<PersonDTO> response = personController.savePerson(personDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void savePersonAdmin_shouldReturnSavedPerson() throws Exception {
		PersonDTO personDTO = new PersonDTO();
		Person person = new Person();
		when(savePersonService.save(any(), any())).thenReturn(person);

		ResponseEntity<PersonDTO> response = personController.savePersonAdmin(personDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void updatePerson_shouldReturnUpdatedPersonWhenFound() throws PersonNotFoundException {
		Long id = 1L;
		PersonDTO personDTO = new PersonDTO();
		when(updatePersonService.update(personDTO, id)).thenReturn(personDTO);

		ResponseEntity<PersonDTO> response = personController.updatePerson(personDTO, id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(personDTO, response.getBody());
	}

	@Test
	void updatePerson_shouldReturnNotFoundWhenPersonNotExists() throws PersonNotFoundException {
		Long id = 1L;
		PersonDTO personDTO = new PersonDTO();
		when(updatePersonService.update(personDTO, id)).thenThrow(new PersonNotFoundException("Person not found"));

		ResponseEntity<PersonDTO> response = personController.updatePerson(personDTO, id);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void deletePerson_shouldReturnOkWhenPersonExists() throws PersonNotFoundException {
		PersonDTO personDTO = new PersonDTO();
		doNothing().when(deletePersonService).delete(personDTO);

		ResponseEntity<Void> response = personController.deletePerson(personDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void deletePerson_shouldReturnNotFoundWhenPersonNotExists() throws PersonNotFoundException {
		PersonDTO personDTO = new PersonDTO();
		doThrow(new PersonNotFoundException("Person not found")).when(deletePersonService).delete(personDTO);

		ResponseEntity<Void> response = personController.deletePerson(personDTO);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void deletePersonById_shouldReturnOkWhenPersonExists() throws PersonNotFoundException {
		Long id = 1L;
		doNothing().when(deletePersonService).delete(id);

		ResponseEntity<Void> response = personController.deletePersonById(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void deletePersonById_shouldReturnNotFoundWhenPersonNotExists() throws PersonNotFoundException {
		Long id = 1L;
		doThrow(new PersonNotFoundException("Person not found")).when(deletePersonService).delete(id);

		ResponseEntity<Void> response = personController.deletePersonById(id);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

}