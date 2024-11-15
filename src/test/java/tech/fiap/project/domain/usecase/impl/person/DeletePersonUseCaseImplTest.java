package tech.fiap.project.domain.usecase.impl.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.domain.entity.Person;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeletePersonUseCaseImplTest {

	@Mock
	private PersonDataProvider personDataProvider;

	@InjectMocks
	private DeletePersonUseCaseImpl deletePersonUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deleteByEmail_deletesPersonSuccessfully() {
		Person person = new Person();
		when(personDataProvider.retrieveByEmail("test@example.com")).thenReturn(Optional.of(person));

		deletePersonUseCaseImpl.delete("test@example.com");

		verify(personDataProvider).delete(person);
	}

	@Test
	void deleteById_deletesPersonSuccessfully() {
		deletePersonUseCaseImpl.delete(1L);

		verify(personDataProvider).delete(1L);
	}

}