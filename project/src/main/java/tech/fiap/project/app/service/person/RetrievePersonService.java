package tech.fiap.project.app.service.person;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.PersonMapper;
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.RetrievePersonUseCase;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RetrievePersonService {

	private RetrievePersonUseCase retrievePersonUseCase;

	public Optional<PersonDTO> findByEmail(String email) {
		Optional<Person> byEmail = retrievePersonUseCase.findByEmail(email);
		return byEmail.map(PersonMapper::toDTO);
	}

	public Optional<PersonDTO> findById(Long id) {
		Optional<Person> byEmail = retrievePersonUseCase.findById(id);
		return byEmail.map(PersonMapper::toDTO);
	}

	public List<PersonDTO> findAll() {
		List<Person> people = retrievePersonUseCase.findAll();
		return people.stream().map(PersonMapper::toDTO).toList();
	}

}
