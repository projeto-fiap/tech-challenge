package tech.fiap.project.app.service.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.domain.usecase.person.DeletePersonUseCase;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class DeletePersonServiceTest {

    @Mock
    private DeletePersonUseCase deletePersonUseCase;

    @InjectMocks
    private DeletePersonService deletePersonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteByEmail() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail("test@example.com");

        deletePersonService.delete(personDTO);

        verify(deletePersonUseCase, times(1)).delete("test@example.com");
    }

    @Test
    void testDeleteById() {
        Long id = 1L;

        deletePersonService.delete(id);

        verify(deletePersonUseCase, times(1)).delete(id);
    }
}
