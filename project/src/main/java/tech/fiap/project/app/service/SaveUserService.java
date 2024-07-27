package tech.fiap.project.app.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.usecase.SaveUserUseCase;

@Slf4j
@Service
@AllArgsConstructor
public class SaveUserService {

	private final SaveUserUseCase saveUserUseCase;

	public User save(User user) {
		try {
			validateUser(user);
			User savedUser = saveUserUseCase.save(user);
			log.info("User saved successfully: {}", savedUser);
			return savedUser;
		}
		catch (IllegalArgumentException e) {
			log.error("Validation error: {}", e.getMessage());
			throw e;
		}
		catch (Exception e) {
			log.error("Unexpected error occurred while saving user: {}", e.getMessage(), e);
			throw new RuntimeException("Failed to save user", e);
		}
	}

	private void validateUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User cannot be null");
		}
		if (!StringUtils.hasText(user.getEmail())) {
			throw new IllegalArgumentException("User email cannot be null or empty");
		}
		if (!StringUtils.hasText(user.getPassword())) {
			throw new IllegalArgumentException("User password cannot be null or empty");
		}
	}

}