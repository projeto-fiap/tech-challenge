package tech.fiap.project.infra.dataprovider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.infra.entity.DocumentEntity;
import tech.fiap.project.infra.entity.PersonEntity;
import tech.fiap.project.infra.exception.PersonAlreadyExistsException;
import tech.fiap.project.infra.repository.DocumentRepository;
import tech.fiap.project.infra.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PersonDataProviderImplTest {

	@Mock
	private PersonRepository personRepository;

	@Mock
	private DocumentRepository documentRepository;

	@InjectMocks
	private PersonDataProviderImpl personDataProvider;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void retrieveAll_shouldReturnListOfPersons() {
		List<PersonEntity> personEntities = List.of(new PersonEntity(), new PersonEntity());
		when(personRepository.findAll()).thenReturn(personEntities);

		List<Person> persons = personDataProvider.retrieveAll();

		assertEquals(personEntities.size(), persons.size());
		verify(personRepository, times(1)).findAll();
	}

	@Test
	void retrieveByCpf_shouldReturnPerson() {
		PersonEntity personEntity = new PersonEntity();
		when(personRepository.findByDocuments_TypeAndDocuments_Value(eq(DocumentType.CPF), eq("12345678900")))
				.thenReturn(Optional.of(personEntity));

		Optional<Person> person = personDataProvider.retrieveByCpf("12345678900");

		assertTrue(person.isPresent());
		verify(personRepository, times(1)).findByDocuments_TypeAndDocuments_Value(DocumentType.CPF, "12345678900");
	}

	@Test
	void retrieveById_shouldReturnPerson() {
		PersonEntity personEntity = new PersonEntity();
		when(personRepository.findById(1L)).thenReturn(Optional.of(personEntity));

		Optional<Person> person = personDataProvider.retrieveById(1L);

		assertTrue(person.isPresent());
		verify(personRepository, times(1)).findById(1L);
	}

	@Test
	void save_shouldSaveAndReturnPerson() {
		Person person = new Person();
		PersonEntity personEntity = new PersonEntity();
		when(documentRepository.findByTypeAndValue(any(), any())).thenReturn(Optional.empty());
		when(personRepository.save(any())).thenReturn(personEntity);

		Person savedPerson = personDataProvider.save(person);

		assertNotNull(savedPerson);
		verify(personRepository, times(1)).save(any());
	}

	@Test
	void delete_shouldDeletePerson() {
		Person person = new Person();
		personDataProvider.delete(person);

		verify(personRepository, times(1)).delete(any());
	}

	@Test
	void update_shouldUpdateAndReturnPerson() {
		Person person = new Person();
		PersonEntity personEntity = new PersonEntity();
		when(personRepository.save(any())).thenReturn(personEntity);

		Person updatedPerson = personDataProvider.update(person);

		assertNotNull(updatedPerson);
		verify(personRepository, times(1)).save(any());
	}

	@Test
	void deleteById_shouldDeletePersonById() {
		personDataProvider.delete(1L);

		verify(personRepository, times(1)).deleteById(1L);
	}

	@Test
	void save_shouldThrowExceptionWhenDocumentAlreadyExists() {
		Person person = new Person();
		Document document = new Document(DocumentType.CPF, "12345678900");
		person.setDocument(List.of(document));

		when(documentRepository.findByTypeAndValue(document.getType(), document.getValue()))
				.thenReturn(Optional.of(new DocumentEntity()));

		PersonAlreadyExistsException exception = assertThrows(PersonAlreadyExistsException.class, () -> {
			personDataProvider.save(person);
		});

		verify(documentRepository, times(1)).findByTypeAndValue(document.getType(), document.getValue());
		verify(personRepository, never()).save(any());
	}

	@Test
	void save_shouldSavePersonWhenDocumentDoesNotExist() {
		Person person = new Person();
		Document document = new Document(DocumentType.CPF, "12345678900");
		person.setDocument(List.of(document));
		PersonEntity personEntity = new PersonEntity();

		when(documentRepository.findByTypeAndValue(document.getType(), document.getValue()))
				.thenReturn(Optional.empty());
		when(personRepository.save(any())).thenReturn(personEntity);

		Person savedPerson = personDataProvider.save(person);

		assertNotNull(savedPerson);
		verify(documentRepository, times(1)).findByTypeAndValue(document.getType(), document.getValue());
		verify(personRepository, times(1)).save(any());
	}


}
