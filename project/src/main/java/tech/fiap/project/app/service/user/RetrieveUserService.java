package tech.fiap.project.app.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.UserMapper;
import tech.fiap.project.app.dto.UserDTO;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.usecase.user.RetrieveUserUseCase;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RetrieveUserService {

	private RetrieveUserUseCase retrieveUserUseCase;

	public Optional<UserDTO> findByEmail(String email) {
		Optional<User> byEmail = retrieveUserUseCase.findByEmail(email);
		return byEmail.map(UserMapper::toDTO);
	}

	public Optional<UserDTO> findById(Long id) {
		Optional<User> byEmail = retrieveUserUseCase.findById(id);
		return byEmail.map(UserMapper::toDTO);
	}

	public List<UserDTO> findAll() {
		List<User> users = retrieveUserUseCase.findAll();
		return users.stream().map(UserMapper::toDTO).toList();
	}

}
