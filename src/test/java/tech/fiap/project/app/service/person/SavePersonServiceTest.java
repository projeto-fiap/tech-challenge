package tech.fiap.project.app.service.person;

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
		person.setDocument(List.of(new Document(DocumentType.CPF,"12345")));

		documents = person.getDocument();
	}

	@Test
	void testSavePerson_Success() {
		when(savePersonUseCase.save(any(Person.class))).thenReturn(person);

		Person savedPerson = savePersonService.save(person, documents);

		assertNotNull(savedPerson);
		assertEquals("12345", savedPerson.getDocument().get(0).getValue());
		verify(savePersonUseCase, times(1)).save(any(Person.class));
	}

	@Test
	void testSavePerson_PersonAlreadyExistsException() {
		when(savePersonUseCase.save(any(Person.class))).thenThrow(new PersonAlreadyExistsException("12345"));

		PersonAlreadyExistsException exception = assertThrows(PersonAlreadyExistsException.class, () -> {
			savePersonService.save(person, documents);
		});

		assertEquals("Documento com o valor [12345] já existe", exception.getMessage());
		verify(savePersonUseCase, times(1)).save(any(Person.class));
	}

	@Test
	void testSavePerson_EncodePassword() {
		String plainPassword = person.getPassword();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String encodedPassword = passwordEncoder.encode(plainPassword);

		savePersonService = new SavePersonService(savePersonUseCase);

		when(savePersonUseCase.save(any(Person.class))).thenReturn(person);

		person.setPassword(plainPassword);

		Person savedPerson = savePersonService.save(person, documents);

		assertNotNull(savedPerson.getPassword());
		assertTrue(passwordEncoder.matches(plainPassword, savedPerson.getPassword())); // Verificar se o hash corresponde
		verify(savePersonUseCase, times(1)).save(any(Person.class));
	}


}
