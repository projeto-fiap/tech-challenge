package tech.integration.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import tech.fiap.project.app.service.person.SavePersonService;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.SavePersonUseCase;
import tech.fiap.project.infra.configuration.Configuration;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = { Configuration.class, ServletWebServerFactoryAutoConfiguration.class })
@ActiveProfiles("integration-test")
public class SavePersonServiceIntegrationTest {

	@Autowired
	private SavePersonService savePersonService;

	@MockBean
	private SavePersonUseCase savePersonUseCase;

	private Person person;

	private List<Document> documents;

	@BeforeEach
	void setUp() {
		person = new Person();
		person.setName("John Doe");
		person.setEmail("john.doe@example.com");
		person.setPassword("password123");

		Document document = new Document();
		document.setType(DocumentType.CPF);
		document.setValue("12345678900");
		documents = Collections.singletonList(document);
	}

	@Test
	void save_shouldReturnPerson_whenSuccessful() {
		when(savePersonUseCase.save(any(Person.class))).thenReturn(person);

		Person savedPerson = savePersonService.save(person, documents);

		assertNotNull(savedPerson);
		assertEquals("John Doe", savedPerson.getName());
		assertEquals("john.doe@example.com", savedPerson.getEmail());
		assertNotNull(savedPerson.getPassword()); // Password should be encoded
		verify(savePersonUseCase, times(1)).save(any(Person.class));
	}

	@Test
	void save_shouldThrowRuntimeException_whenUnexpectedErrorOccurs() {
		when(savePersonUseCase.save(any(Person.class)))
				.thenThrow(new RuntimeException("Any error message"));

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			savePersonService.save(person, documents);
		});

		assertNotNull(exception);
		assertEquals("Any error message", exception.getMessage());

		verify(savePersonUseCase, times(1)).save(any(Person.class));
	}
}
