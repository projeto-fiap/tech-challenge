package tech.fiap.project.app.service.person;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.SavePersonUseCase;
import tech.fiap.project.infra.exception.PersonAlreadyExistsException;

import java.util.List;

@AllArgsConstructor
@Service
public class SavePersonService {

	private SavePersonUseCase savePersonUseCase;

	public Person save(Person person, List<Document> documents) {
		try {
			person.setDocument(documents);
			return savePersonUseCase.save(person);
		}
		catch (PersonAlreadyExistsException e) {
			throw new PersonAlreadyExistsException(person.getDocument().get(0).getValue());
		}
		catch (Exception e) {
			throw new RuntimeException("Erro ao salvar usuário: " + e.getMessage());
		}
	}

}
