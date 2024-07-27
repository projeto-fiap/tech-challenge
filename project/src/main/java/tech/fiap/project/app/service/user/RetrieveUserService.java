package tech.fiap.project.app.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tech.fiap.project.app.adapter.UserMapper;
import tech.fiap.project.app.dto.UserDTO;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.usecase.user.RetrieveUserUseCase;
import tech.fiap.project.infra.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class RetrieveUserService {

	private final RetrieveUserUseCase retrieveUserUseCase;

	public Optional<UserDTO> findByEmail(String email) {
		try {
			validateEmail(email);
			Optional<User> byEmail = retrieveUserUseCase.findByEmail(email);
			log.info("User retrieved successfully by email: {}", email);
			if (byEmail.isEmpty()) {
				throw new UserNotFoundException(email,null);
			}
			return byEmail.map(UserMapper::toDTO);
		}
		catch (IllegalArgumentException e) {
			log.error("Validation error: {}", e.getMessage());
			throw e;
		}
		catch (Exception e) {
			log.error("Unexpected error occurred while retrieving user by email: {}", e.getMessage(), e);
			throw new RuntimeException("Failed to retrieve user by email", e);
		}
	}

	public Optional<UserDTO> findById(Long id) {
		try {
			validateId(id);
			Optional<User> byId = retrieveUserUseCase.findById(id);
			log.info("User retrieved successfully by id: {}", id);
			return byId.map(UserMapper::toDTO);
		}
		catch (IllegalArgumentException e) {
			log.error("Validation error: {}", e.getMessage());
			throw e;
		}
		catch (Exception e) {
			log.error("Unexpected error occurred while retrieving user by id: {}", e.getMessage(), e);
			throw new RuntimeException("Failed to retrieve user by id", e);
		}
	}

	public List<UserDTO> findAll() {
		try {
			List<User> users = retrieveUserUseCase.findAll();
			log.info("All users retrieved successfully");
			return users.stream().map(UserMapper::toDTO).toList();
		}
		catch (Exception e) {
			log.error("Unexpected error occurred while retrieving all users: {}", e.getMessage(), e);
			throw new RuntimeException("Failed to retrieve all users", e);
		}
	}

	private void validateEmail(String email) {
		if (!StringUtils.hasText(email)) {
			throw new IllegalArgumentException("Email cannot be null or empty");
		}
	}

	private void validateId(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("ID cannot be null");
		}
	}

}
