package tech.fiap.project.domain.dataprovider;

import tech.fiap.project.domain.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDataProvider {

	List<Person> retrieveAll();

	Optional<Person> retrieveByEmail(String email);

	Optional<Person> retrieveByCPF(String cpf);

	Optional<Person> retrieveById(Long id);

	Person save(Person person);

	void delete(Person person);

	void delete(Long id);

}
