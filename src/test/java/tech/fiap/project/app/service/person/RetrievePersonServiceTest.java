package tech.fiap.project.app.service.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.dto.DocumentDTO;
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.RetrievePersonUseCase;

import java.util.Collections;
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
	void findByCpf_shouldReturnPersonDTO_whenPersonExists() {
		String cpf = "12345678900";
		Document document = new Document(DocumentType.CPF, cpf);
		Person person = new Person();
		person.setDocument(Collections.singletonList(document));

		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setType(DocumentType.CPF);
		documentDTO.setValue(cpf);
		PersonDTO personDTO = new PersonDTO();
		personDTO.setDocument(Collections.singletonList(documentDTO));

		when(retrievePersonUseCase.findByCpf(cpf)).thenReturn(Optional.of(person));

		Optional<PersonDTO> result = retrievePersonService.findByCpf(cpf);

		assertTrue(result.isPresent());
		assertEquals(personDTO.getDocument(), result.get().getDocument());
	}

	@Test
	void findById_shouldReturnPersonDTO_whenPersonExists() {
		Long id = 1L;
		Person person = new Person();
		person.setId(id);
		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(id);
		personDTO.setDocument(Collections.emptyList());
		when(retrievePersonUseCase.findById(id)).thenReturn(Optional.of(person));

		Optional<PersonDTO> result = retrievePersonService.findById(id);

		assertTrue(result.isPresent());
		assertEquals(personDTO.getId(), result.get().getId());
	}

	@Test
	void findAll_shouldReturnListOfPersonDTO() {
		Person person = new Person();
		person.setId(1L);
		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(1L);
		personDTO.setDocument(Collections.emptyList());
		when(retrievePersonUseCase.findAll()).thenReturn(List.of(person));

		List<PersonDTO> result = retrievePersonService.findAll();

		assertEquals(1, result.size());
		assertEquals(personDTO.getId(), result.get(0).getId());
	}

}
