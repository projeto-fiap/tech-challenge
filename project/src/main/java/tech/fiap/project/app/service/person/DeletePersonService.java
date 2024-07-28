package tech.fiap.project.app.service.person;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.domain.usecase.person.DeletePersonUseCase;

@AllArgsConstructor
@Service
public class DeletePersonService {

	private DeletePersonUseCase deletePersonService;

	public void delete(PersonDTO personDTO) {
		deletePersonService.delete(personDTO.getEmail());
	}

}
