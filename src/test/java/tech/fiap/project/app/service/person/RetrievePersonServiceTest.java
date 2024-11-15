package tech.fiap.project.app.service.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.RetrievePersonUseCase;
import tech.fiap.project.infra.exception.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RetrievePersonServiceTest {

	@Mock
	private RetrievePersonUseCase retrievePersonUseCase;

	@InjectMocks
	private RetrievePersonService retrievePersonService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findByEmail_shouldReturnPersonDTO_whenPersonExists() {
		String email = "test@example.com";
		Person person = new Person();
		person.setEmail(email);
		PersonDTO personDTO = new PersonDTO();
		personDTO.setEmail(email);

		when(retrievePersonUseCase.findByEmail(email)).thenReturn(Optional.of(person));

		Optional<PersonDTO> result = retrievePersonService.findByEmail(email);

		assertTrue(result.isPresent());
		assertEquals(personDTO, result.get());
	}

	@Test
	void findById_shouldReturnPersonDTO_whenPersonExists() {
		Long id = 1L;
		Person person = new Person();
		person.setId(id);
		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(id);

		when(retrievePersonUseCase.findById(id)).thenReturn(Optional.of(person));

		Optional<PersonDTO> result = retrievePersonService.findById(id);

		assertTrue(result.isPresent());
		assertEquals(personDTO, result.get());
	}

	@Test
	void findAll_shouldReturnListOfPersonDTO() {
		Person person = new Person();
		person.setId(1L);
		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(1L);

		when(retrievePersonUseCase.findAll()).thenReturn(List.of(person));

		List<PersonDTO> result = retrievePersonService.findAll();

		assertEquals(1, result.size());
		assertEquals(personDTO, result.get(0));
	}

}