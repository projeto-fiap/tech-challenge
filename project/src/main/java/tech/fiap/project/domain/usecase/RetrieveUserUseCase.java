package tech.fiap.project.domain.usecase;

import tech.fiap.project.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface RetrieveUserUseCase {

	Optional<User> findByEmail(String email);

	Optional<User> findById(Long id);

	List<User> findAll();

}
