package tech.fiap.project.domain.usecase.impl.person;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.fiap.project.app.dto.OrderRequestDTO;
import tech.fiap.project.domain.dataprovider.PersonDataProvider;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.Person;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InitializePersonUseCaseImplTest {

	@Mock
	private PersonDataProvider personDataProvider;

	@InjectMocks
	private InitializePersonUseCaseImpl initializePersonUseCase;

	private OrderRequestDTO orderRequestDTO;

	private Person person;

	private Document document;

	@BeforeEach
	void setUp() {
		document = new Document();
		document.setType(DocumentType.CPF);
		document.setValue("12345678901");

		person = new Person();
		person.setDocument(Collections.singletonList(document));

		orderRequestDTO = new OrderRequestDTO();
		orderRequestDTO.setPerson(person);
	}

	@Test
	void testExecute_PersonFound() throws BadRequestException {
		when(personDataProvider.retrieveByCpf("12345678901")).thenReturn(Optional.of(person));

		initializePersonUseCase.execute(orderRequestDTO);

		assertEquals(person, orderRequestDTO.getPerson());
		verify(personDataProvider).retrieveByCpf("12345678901");
	}



	@Test
	void testExecute_InvalidPersonDocument() {
		document.setValue(null);

		assertThrows(BadRequestException.class, () -> initializePersonUseCase.execute(orderRequestDTO));
	}

	@Test
	void testExecute_InvalidPerson() {
		orderRequestDTO.setPerson(null);

		assertDoesNotThrow(() -> initializePersonUseCase.execute(orderRequestDTO));
	}

	@Test
	void testExecute_NoDocuments() {
		person.setDocument(Collections.emptyList());

		assertThrows(BadRequestException.class, () -> initializePersonUseCase.execute(orderRequestDTO));
	}

	@Test
	void testExecute_InvalidDocumentType() {
		document.setType(null);

		assertThrows(BadRequestException.class, () -> initializePersonUseCase.execute(orderRequestDTO));
	}

	@Test
	void testExecute_InvalidDocumentValue() {
		document.setValue(null);

		assertThrows(BadRequestException.class, () -> initializePersonUseCase.execute(orderRequestDTO));
	}

}