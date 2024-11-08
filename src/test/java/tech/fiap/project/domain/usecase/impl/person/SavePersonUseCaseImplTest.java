package tech.fiap.project.domain.usecase.impl.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.domain.entity.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SavePersonUseCaseImplTest {

	@Mock
	private PersonDataProvider personDataProvider;

	@InjectMocks
	private SavePersonUseCaseImpl savePersonUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void save_returnsSavedPerson() {
		Person person = new Person();
		when(personDataProvider.save(person)).thenReturn(person);

		Person result = savePersonUseCaseImpl.save(person);

		assertEquals(person, result);
		verify(personDataProvider).save(person);
	}

}