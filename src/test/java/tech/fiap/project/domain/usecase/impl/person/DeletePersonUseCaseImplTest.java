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
	void deleteByCpf_deletesPersonSuccessfully() {
		String cpf = "12345678900";
		Person person = new Person();
		when(personDataProvider.retrieveByCpf(cpf)).thenReturn(Optional.of(person));

		deletePersonUseCaseImpl.delete(cpf);

		verify(personDataProvider).delete(person);
	}

	@Test
	void deleteById_deletesPersonSuccessfully() {
		Long id = 1L;
		deletePersonUseCaseImpl.delete(id);

		verify(personDataProvider).delete(id);
	}

}
