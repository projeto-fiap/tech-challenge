package tech.fiap.project.domain.usecase.person;

import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.domain.entity.Person;

public interface UpdatePersonUseCase {

	Person update(Long id, PersonDTO personDTO);

}
