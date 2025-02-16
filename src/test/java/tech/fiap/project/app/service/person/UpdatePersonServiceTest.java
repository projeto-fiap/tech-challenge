package tech.fiap.project.app.service.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;
import tech.fiap.project.app.adapter.PersonMapper;
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.UpdatePersonUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdatePersonServiceTest {

	@Mock
	private UpdatePersonUseCase updatePersonUseCase;

	@InjectMocks
	private UpdatePersonService updatePersonService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testUpdate() {
		Long id = 1L;
		PersonDTO personDTO = new PersonDTO();
		Person person = new Person();

		when(updatePersonUseCase.update(id, personDTO)).thenReturn(person);

		try (MockedStatic<PersonMapper> mockedMapper = mockStatic(PersonMapper.class)) {
			mockedMapper.when(() -> PersonMapper.toDTO(person)).thenReturn(personDTO);

			PersonDTO updatedPerson = updatePersonService.update(personDTO, id);

			assertThat(updatedPerson).isEqualTo(personDTO);
			verify(updatePersonUseCase, times(1)).update(id, personDTO);
			mockedMapper.verify(() -> PersonMapper.toDTO(person), times(1));
		}
	}

}
