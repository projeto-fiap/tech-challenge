package tech.fiap.project.app.service.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.SavePersonUseCase;
import tech.fiap.project.infra.exception.PersonAlreadyExistsException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SavePersonServiceTest {

	@Mock
	private SavePersonUseCase savePersonUseCase;

	@InjectMocks
	private SavePersonService savePersonService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void save_shouldReturnSavedPerson_whenPersonIsSavedSuccessfully() {
		Person person = new Person();
		Document document = new Document();
		document.setValue("123456789");
		List<Document> documents = List.of(document);
		person.setDocument(documents);

		when(savePersonUseCase.save(person)).thenReturn(person);

		Person result = savePersonService.save(person, documents);

		assertEquals(person, result);
	}

	@Test
	void save_shouldThrowPersonAlreadyExistsException_whenPersonAlreadyExists() {
		Person person = new Person();
		Document document = new Document();
		document.setValue("123456789");
		List<Document> documents = List.of(document);
		person.setDocument(documents);

		when(savePersonUseCase.save(person)).thenThrow(new PersonAlreadyExistsException("123456789"));

		assertThrows(PersonAlreadyExistsException.class, () -> savePersonService.save(person, documents));
	}

	@Test
	void save_shouldThrowRuntimeException_whenAnUnexpectedErrorOccurs() {
		Person person = new Person();
		Document document = new Document();
		document.setValue("123456789");
		List<Document> documents = List.of(document);
		person.setDocument(documents);

		when(savePersonUseCase.save(person)).thenThrow(new RuntimeException("Unexpected error"));

		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> savePersonService.save(person, documents));
		assertEquals("Erro ao salvar usuário: Unexpected error", exception.getMessage());
	}

}