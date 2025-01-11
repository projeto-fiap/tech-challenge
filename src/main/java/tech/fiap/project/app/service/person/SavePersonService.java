package tech.fiap.project.app.service.person;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.SavePersonUseCase;
import tech.fiap.project.infra.exception.PersonAlreadyExistsException;
import tech.fiap.project.infra.exception.PersonSaveException;

import java.util.List;

@AllArgsConstructor
@Service
public class SavePersonService {

	private SavePersonUseCase savePersonUseCase;

	public Person save(Person person, List<Document> documents) {
		try {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(person.getPassword());
			person.setDocument(documents);
			person.setPassword(encodedPassword);
			return savePersonUseCase.save(person);
		}
		catch (PersonAlreadyExistsException e) {
			throw new PersonAlreadyExistsException(person.getDocument().get(0).getValue());
		}
		catch (Exception e) {
			throw new PersonSaveException(e.getMessage());
		}
	}

}
