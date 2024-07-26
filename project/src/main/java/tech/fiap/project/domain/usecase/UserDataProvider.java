package tech.fiap.project.domain.usecase;

import tech.fiap.project.domain.entity.User;
import tech.fiap.project.infra.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserDataProvider {

	List<User> retrieveAll();

	Optional<User> retrieveByEmail(String email);

	Optional<User> retrieveByCPF(String cpf);

	Optional<User> retrieveById(Long id);

	User save(User user);

	void delete(User user);

}
