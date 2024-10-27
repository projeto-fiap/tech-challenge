package tech.fiap.project.domain.usecase.person;

import tech.fiap.project.domain.entity.Person;

import java.util.List;
import java.util.Optional;

public interface RetrievePersonUseCase {

	Optional<Person> findByEmail(String email);

	Optional<Person> findById(Long id);

	List<Person> findAll();

}
