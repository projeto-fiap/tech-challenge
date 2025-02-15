package tech.fiap.project.app.service.person;

import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	public Person save(Person person, List<Document> documents) throws BadRequestException {
		try {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(person.getPassword());
			person.setDocument(documents);
			person.setPassword(encodedPassword);
			return savePersonUseCase.save(person);
		}
		catch (PersonAlreadyExistsException e) {
			throw new BadRequestException("Pessa já existe!");
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
