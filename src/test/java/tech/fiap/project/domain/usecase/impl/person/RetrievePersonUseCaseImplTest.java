package tech.fiap.project.domain.usecase.impl.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.domain.entity.Person;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class RetrievePersonUseCaseImplTest {

	@Mock
	private PersonDataProvider personDataProvider;

	@InjectMocks
	private RetrievePersonUseCaseImpl retrievePersonUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findByEmail_returnsPerson() {
		Person person = new Person();
		String email = "test@example.com";
		when(personDataProvider.retrieveByEmail(email)).thenReturn(Optional.of(person));

		Optional<Person> result = retrievePersonUseCaseImpl.findByEmail(email);

		assertTrue(result.isPresent());
		assertEquals(person, result.get());
	}

	@Test
	void findById_returnsPerson() {
		Person person = new Person();
		Long id = 1L;
		when(personDataProvider.retrieveById(id)).thenReturn(Optional.of(person));

		Optional<Person> result = retrievePersonUseCaseImpl.findById(id);

		assertTrue(result.isPresent());
		assertEquals(person, result.get());
	}

	@Test
	void findAll_returnsListOfPersons() {
		Person person = new Person();
		List<Person> persons = Collections.singletonList(person);
		when(personDataProvider.retrieveAll()).thenReturn(persons);

		List<Person> result = retrievePersonUseCaseImpl.findAll();

		assertEquals(persons, result);
	}

	@Test
	void findById_withEmail_returnsPerson() {
		Person person = new Person();
		String email = "test@example.com";
		when(personDataProvider.retrieveByEmail(email)).thenReturn(Optional.of(person));

		Optional<Person> result = retrievePersonUseCaseImpl.findById(email);

		assertTrue(result.isPresent());
		assertEquals(person, result.get());
	}

}