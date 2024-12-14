package tech.integration.fiap.project.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import tech.fiap.project.app.dto.CreateItemRequestDTO;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.Role;
import tech.fiap.project.infra.configuration.Configuration;
import tech.fiap.project.infra.entity.DocumentEntity;
import tech.fiap.project.infra.entity.PersonEntity;
import tech.fiap.project.infra.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = { Configuration.class, ServletWebServerFactoryAutoConfiguration.class })
@ActiveProfiles("integration-test")
class ItemControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PersonRepository personRepository;

	@BeforeEach
	void createUser() {
		if (personRepository.findAll().isEmpty()) {
			PersonEntity personEntity = new PersonEntity();
			personEntity.setName("Usuario Teste Integrado");
			List<DocumentEntity> documentEntities = new ArrayList<>();
			DocumentEntity documentEntity = new DocumentEntity();
			documentEntity.setType(DocumentType.CPF);
			documentEntity.setValue("123456787"); // CPF usado para login
			documentEntity.setPerson(personEntity);
			documentEntities.add(documentEntity);
			personEntity.setDocuments(documentEntities);
			personEntity.setPassword("developer");
			personEntity.setRole(Role.ADMIN);
			personRepository.save(personEntity);
		}
	}

	@Test
	void createItems_shouldReturnCreatedItems_whenSuccessful() {
		CreateItemRequestDTO requestDTO = new CreateItemRequestDTO();
		ResponseEntity<List> response = restTemplate.withBasicAuth("123456787", "developer")
				.postForEntity("/api/v1/items", List.of(requestDTO), List.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void findAll_shouldReturnAllItems_whenSuccessful() {
		ResponseEntity<List> response = restTemplate.withBasicAuth("123456787", "developer")
				.getForEntity("/api/v1/items", List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void findByCategory_shouldReturnItemsByCategory_whenSuccessful() {
		String category = "FOOD";
		ResponseEntity<List> response = restTemplate.withBasicAuth("123456787", "developer")
				.getForEntity("/api/v1/items/category/" + category, List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void updateItem_shouldReturnUpdatedItem_whenSuccessful() {
		long id = 1L;
		CreateItemRequestDTO requestDTO = new CreateItemRequestDTO();
		restTemplate.withBasicAuth("123456787", "developer").postForEntity("/api/v1/items", List.of(requestDTO),
				List.class);

		ItemDTO itemDTO = new ItemDTO();
		HttpEntity<ItemDTO> itemDTOHttpEntity = new HttpEntity<>(itemDTO);
		ResponseEntity<ItemDTO> response = restTemplate.withBasicAuth("123456787", "developer")
				.exchange("/api/v1/items/" + id, HttpMethod.PUT, itemDTOHttpEntity, ItemDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void deleteItem_shouldReturnNoContent_whenSuccessful() {
		long id = 1L;
		ResponseEntity<Void> response = restTemplate.withBasicAuth("123456787", "developer")
				.exchange("/api/v1/items/" + id, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

}