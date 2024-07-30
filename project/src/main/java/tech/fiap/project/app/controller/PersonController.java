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
@RequestMapping("api/v1/person")
@AllArgsConstructor
public class PersonController {

	private RetrievePersonService retrievePersonService;

	private UpdatePersonService updatePersonService;

	private SavePersonService savePersonService;

	private DeletePersonService deletePersonService;

	@GetMapping("/{email}")
	private ResponseEntity<PersonDTO> getPerson(@PathVariable String email) {
	@GetMapping("/email/{email}")
	private ResponseEntity<PersonDTO> getUser(@PathVariable String email) {
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
	private ResponseEntity<PersonDTO> savePerson(PersonDTO person) {
		Person personSaved = savePersonService.save(PersonMapper.toDomain(person));
		return ResponseEntity.ok(PersonMapper.toDTO(personSaved));
	}

	@PutMapping
	private ResponseEntity<PersonDTO> updatePerson(PersonDTO person) {
		try {
			PersonDTO update = updatePersonService.update(person);
			return ResponseEntity.ok(update);
		}
		catch (PersonNotFoundException personNotFoundException) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	private ResponseEntity<Void> deletePerson(PersonDTO person) {
		try {
			deletePersonService.delete(person);
			return ResponseEntity.ok().build();
		}
		catch (PersonNotFoundException personNotFoundException) {
			return ResponseEntity.notFound().build();
		}
	}

}
