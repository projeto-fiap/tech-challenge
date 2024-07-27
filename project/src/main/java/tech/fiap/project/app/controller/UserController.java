package tech.fiap.project.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.adapter.UserMapper;
import tech.fiap.project.app.dto.UserDTO;
import tech.fiap.project.app.service.user.DeleteUserService;
import tech.fiap.project.app.service.user.RetrieveUserService;
import tech.fiap.project.app.service.user.SaveUserService;
import tech.fiap.project.app.service.user.UpdateUserService;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.infra.exception.UserNotFoundException;
import tech.fiap.project.infra.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

	private final UserRepository userRepository;

	private RetrieveUserService retrieveUserService;

	private UpdateUserService updateUserService;

	private SaveUserService saveUserService;

	private DeleteUserService deleteUserService;

	@GetMapping("/{email}")
	private ResponseEntity<UserDTO> getUser(@PathVariable String email) {
		Optional<UserDTO> byEmail = retrieveUserService.findByEmail(email);
		return byEmail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}")
	private ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		Optional<UserDTO> byId = retrieveUserService.findById(id);
		return byId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	private ResponseEntity<List<UserDTO>> getUsers() {
		return ResponseEntity.ok(retrieveUserService.findAll());
	}

	@PostMapping
	private ResponseEntity<UserDTO> saveUser(UserDTO user) {
		User userSaved = saveUserService.save(UserMapper.toDomain(user));
		return ResponseEntity.ok(UserMapper.toDTO(userSaved));
	}

	@PutMapping
	private ResponseEntity<UserDTO> updateUser(UserDTO user) {
		try {
			UserDTO update = updateUserService.update(user);
			return ResponseEntity.ok(update);
		}
		catch (UserNotFoundException userNotFoundException) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	private ResponseEntity<Void> deleteUser(UserDTO user) {
		try {
			deleteUserService.delete(user);
			return ResponseEntity.ok().build();
		}
		catch (UserNotFoundException userNotFoundException) {
			return ResponseEntity.notFound().build();
		}
	}

}
