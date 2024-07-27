package tech.fiap.project.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.adapter.PersonMapper;
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.app.service.person.DeletePersonService;
import tech.fiap.project.app.service.person.RetrievePersonService;
import tech.fiap.project.app.service.person.SavePersonService;
import tech.fiap.project.app.service.person.UpdatePersonService;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.infra.exception.PersonNotFoundException;
import tech.fiap.project.infra.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class PersonController {

	private RetrievePersonService retrievePersonService;

	private UpdatePersonService updateUserService;

	private SavePersonService savePersonService;

	private DeletePersonService deletePersonService;

	@GetMapping("/{email}")
	private ResponseEntity<PersonDTO> getUser(@PathVariable String email) {
		Optional<PersonDTO> byEmail = retrievePersonService.findByEmail(email);
		return byEmail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}")
	private ResponseEntity<PersonDTO> getUser(@PathVariable Long id) {
		Optional<PersonDTO> byId = retrievePersonService.findById(id);
		return byId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	private ResponseEntity<List<PersonDTO>> getUsers() {
		return ResponseEntity.ok(retrievePersonService.findAll());
	}

	@PostMapping
	private ResponseEntity<PersonDTO> saveUser(PersonDTO user) {
		Person personSaved = savePersonService.save(PersonMapper.toDomain(user));
		return ResponseEntity.ok(PersonMapper.toDTO(personSaved));
	}

	@PutMapping
	private ResponseEntity<PersonDTO> updateUser(PersonDTO user) {
		try {
			PersonDTO update = updateUserService.update(user);
			return ResponseEntity.ok(update);
		}
		catch (PersonNotFoundException personNotFoundException) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	private ResponseEntity<Void> deleteUser(PersonDTO user) {
		try {
			deletePersonService.delete(user);
			return ResponseEntity.ok().build();
		}
		catch (PersonNotFoundException personNotFoundException) {
			return ResponseEntity.notFound().build();
		}
	}

}
