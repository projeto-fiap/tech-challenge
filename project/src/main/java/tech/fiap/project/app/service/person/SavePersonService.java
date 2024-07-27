package tech.fiap.project.app.service.person;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.SavePersonUseCase;

@AllArgsConstructor
@Service
public class SavePersonService {

	private SavePersonUseCase savePersonUseCase;

	public Person save(Person person) {
		return savePersonUseCase.save(person);
	}

}
