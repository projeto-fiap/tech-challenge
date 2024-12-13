package tech.fiap.project.domain.usecase.impl.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.Person;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InitializePersonUseCaseImplTest {

	@Mock
	private PersonDataProvider personDataProvider;

	@InjectMocks
	private InitializePersonUseCaseImpl initializePersonUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_setsPersonSuccessfully() {
		Document document = new Document(DocumentType.CPF, "12345678900");
		Person person = new Person();
		person.setDocument(Collections.singletonList(document));
		Order order = new Order(1L, null, LocalDateTime.now(), LocalDateTime.now(), null, null, Duration.ZERO, person,
				BigDecimal.TEN);
		when(personDataProvider.retrieveByCpf("12345678900")).thenReturn(Optional.of(person));

		initializePersonUseCaseImpl.execute(order);

		verify(personDataProvider).retrieveByCpf("12345678900");
	}

}
