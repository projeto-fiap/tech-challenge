package tech.fiap.project.app.service.person;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.SavePersonUseCase;
import tech.fiap.project.infra.exception.PersonAlreadyExistsException;
import tech.fiap.project.infra.exception.PersonSaveException;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SavePersonServiceTest {

	@Mock
	private SavePersonUseCase savePersonUseCase;

	@InjectMocks
	private SavePersonService savePersonService;

	private Person person;

	private List<Document> documents;

	@BeforeEach
	void setUp() {
		person = new Person();
		person.setPassword("password123");
		person.setDocument(List.of(new Document(DocumentType.CPF, "12345")));

		documents = person.getDocument();
	}

	@Test
	void testSavePerson_Success() throws BadRequestException {
		when(savePersonUseCase.save(any(Person.class))).thenReturn(person);

		Person savedPerson = savePersonService.save(person, documents);

		assertNotNull(savedPerson);
		assertEquals("12345", savedPerson.getDocument().get(0).getValue());
		verify(savePersonUseCase, times(1)).save(any(Person.class));
	}

	@Test
	void testSavePerson_EncodePassword() throws BadRequestException {
		String plainPassword = person.getPassword();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String encodedPassword = passwordEncoder.encode(plainPassword);

		savePersonService = new SavePersonService(savePersonUseCase);

		when(savePersonUseCase.save(any(Person.class))).thenReturn(person);

		person.setPassword(plainPassword);

		Person savedPerson = savePersonService.save(person, documents);

		assertNotNull(savedPerson.getPassword());
		assertTrue(passwordEncoder.matches(plainPassword, savedPerson.getPassword())); // Verificar
																						// se
																						// o
																						// hash
																						// corresponde
		verify(savePersonUseCase, times(1)).save(any(Person.class));
	}

	@Test
	void testSavePersonThrowsPersonAlreadyExistsException() {
		// Arrange
		doThrow(new PersonAlreadyExistsException("Person already exists"))
				.when(savePersonUseCase).save(any(Person.class));

		// Act & Assert
		assertThrows(BadRequestException.class, () -> {
			savePersonService.save(person, documents);
		});
	}

	@Test
	void testSavePersonThrowsBadRequestException() {
		// Arrange
		doThrow(new RuntimeException("Some error"))
				.when(savePersonUseCase).save(any(Person.class));

		// Act & Assert
		assertThrows(RuntimeException.class, () -> {
			savePersonService.save(person, documents);
		});
	}
}
