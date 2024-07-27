package tech.fiap.project.app.service.person;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.PersonMapper;
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.domain.usecase.person.UpdatePersonUseCase;

@AllArgsConstructor
@Service
public class UpdatePersonService {

	private UpdatePersonUseCase updatePersonUseCase;

	public PersonDTO update(PersonDTO personDTO) {
		return PersonMapper.toDTO(updatePersonUseCase.update(personDTO.getEmail()));
	}

}
