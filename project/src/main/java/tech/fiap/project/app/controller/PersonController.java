package tech.fiap.project.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.adapter.DocumentMapper;
import tech.fiap.project.app.adapter.PersonMapper;
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.app.service.person.DeletePersonService;
import tech.fiap.project.app.service.person.RetrievePersonService;
import tech.fiap.project.app.service.person.SavePersonService;
import tech.fiap.project.app.service.person.UpdatePersonService;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.domain.entity.Role;
import tech.fiap.project.infra.exception.PersonNotFoundException;
import tech.fiap.project.infra.exception.UnauthorizedException;
import tech.fiap.project.infra.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/person")
@AllArgsConstructor
public class PersonController {

	private RetrievePersonService retrievePersonService;

	private UpdatePersonService updatePersonService;

	private SavePersonService savePersonService;

	private DeletePersonService deletePersonService;

	@GetMapping("/email")
	private ResponseEntity<PersonDTO> getPerson(@RequestParam String email) {
		Optional<PersonDTO> byEmail = retrievePersonService.findByEmail(email);
		return byEmail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}")
	private ResponseEntity<PersonDTO> getPerson(@PathVariable Long id) {
		Optional<PersonDTO> byId = retrievePersonService.findById(id);
		return byId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	private ResponseEntity<List<PersonDTO>> getPersons() {
		return ResponseEntity.ok(retrievePersonService.findAll());
	}

	@PostMapping
	private ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO person) {
		if (person.getRole().equals(Role.USER)) {
			Person personSaved = savePersonService.save(PersonMapper.toDomain(person),
					DocumentMapper.toDomain(person.getDocument()));
			return ResponseEntity.ok(PersonMapper.toDTO(personSaved));
		}
		throw new UnauthorizedException(null, HttpStatus.FORBIDDEN);
	}

	@PostMapping("/admin")
	private ResponseEntity<PersonDTO> savePersonAdmin(@RequestBody PersonDTO person) {
		Person personSaved = savePersonService.save(PersonMapper.toDomain(person),
				DocumentMapper.toDomain(person.getDocument()));
		return ResponseEntity.ok(PersonMapper.toDTO(personSaved));
	}

	@PutMapping("/{id}")
	private ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO person, @PathVariable Long id) {
		try {
			PersonDTO update = updatePersonService.update(person, id);
			return ResponseEntity.ok(update);
		}
		catch (PersonNotFoundException personNotFoundException) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	private ResponseEntity<Void> deletePerson(@RequestBody PersonDTO person) {
		try {
			deletePersonService.delete(person);
			return ResponseEntity.ok().build();
		}
		catch (PersonNotFoundException personNotFoundException) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<Void> deletePersonById(@PathVariable Long id) {
		try {
			deletePersonService.delete(id);
			return ResponseEntity.ok().build();
		}
		catch (PersonNotFoundException personNotFoundException) {
			return ResponseEntity.notFound().build();
		}
	}

}
