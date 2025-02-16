package tech.features.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.fiap.project.domain.entity.Document;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.app.service.person.SavePersonService;
import tech.fiap.project.domain.entity.Role;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SavePersonFlow {

	@Autowired
	private SavePersonService savePersonService;

	private Person person;

	private List<Document> documents;

	private Person savedPerson;

	@Given("a valid person and a list of documents")
	public void aValidPersonAndAListOfDocuments() {
		person = new Person();
		person.setName("John Doe");
		person.setEmail("john.doe@example.com");
		person.setPassword("password123");
		person.setRole(Role.USER); // Defina um valor para o campo role

		Document document1 = new Document();
		document1.setType(DocumentType.CPF);
		document1.setValue("12345678900");

		Document document2 = new Document();
		document1.setType(DocumentType.CPF);
		document2.setValue("987654321");

		documents = List.of(document1, document2);
	}

	@When("the person is saved")
	public void thePersonIsSaved() throws Exception {
		savedPerson = savePersonService.save(person, documents);
	}

	@Then("the person should be saved with encrypted password")
	public void thePersonShouldBeSavedWithEncryptedPassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		assertTrue(passwordEncoder.matches("password123", savedPerson.getPassword()),
				"A senha não foi criptografada corretamente!");
	}

}