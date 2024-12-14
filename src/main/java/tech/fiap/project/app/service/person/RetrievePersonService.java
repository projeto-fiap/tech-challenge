package tech.fiap.project.app.service.person;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.PersonMapper;
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.usecase.person.RetrievePersonUseCase;
import tech.fiap.project.infra.exception.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RetrievePersonService {

	private RetrievePersonUseCase retrievePersonUseCase;

	public Optional<PersonDTO> findByCpf(String cpf) {
		Optional<Person> byCpf = retrievePersonUseCase.findByCpf(cpf);
		if (byCpf.isEmpty()) {
			throw new PersonNotFoundException(Optional.of(cpf));
		}
		return byCpf.map(PersonMapper::toDTO);
	}

	public Optional<PersonDTO> findById(Long id) {
		Optional<Person> byId = retrievePersonUseCase.findById(id);
		if (byId.isEmpty()) {
			throw new PersonNotFoundException(id.toString());
		}
		return byId.map(PersonMapper::toDTO);
	}

	public List<PersonDTO> findAll() {
		List<Person> people = retrievePersonUseCase.findAll();
		return people.stream().map(PersonMapper::toDTO).toList();
	}

}
