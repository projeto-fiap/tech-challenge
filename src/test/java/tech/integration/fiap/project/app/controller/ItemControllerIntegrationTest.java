package tech.integration.fiap.project.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import tech.fiap.project.app.dto.CreateItemRequestDTO;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.Role;
import tech.fiap.project.infra.configuration.Configuration;
import tech.fiap.project.infra.configuration.authorization.util.JwtUtil;
import tech.fiap.project.infra.entity.DocumentEntity;
import tech.fiap.project.infra.entity.ItemCategory;
import tech.fiap.project.infra.entity.PersonEntity;
import tech.fiap.project.infra.repository.PersonRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Configuration.class })
@ActiveProfiles("integration-test")
class ItemControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private JwtUtil jwtUtil;

	private String validJwt;

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

			validJwt = jwtUtil.generateToken(personEntity.getDocuments().stream().findFirst().get().getValue());
		}
	}

	private HttpHeaders createHeadersWithJwt() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(validJwt);
		return headers;
	}

	@Test
	@DirtiesContext
	void createItems_shouldReturnCreatedItems_whenSuccessful() {
		CreateItemRequestDTO requestDTO = new CreateItemRequestDTO();
		requestDTO.setName("Test Item");
		requestDTO.setUnit("kg");
		requestDTO.setPrice(BigDecimal.valueOf(10.0));
		requestDTO.setQuantity(BigDecimal.valueOf(2));
		requestDTO.setCategory(ItemCategory.FOOD);
		requestDTO.setDescription("Delicious food item");
		requestDTO.setImageUrl("http://example.com/image.jpg");

		CreateItemRequestDTO ingredient = new CreateItemRequestDTO();
		ingredient.setName("Salt");
		ingredient.setUnit("g");
		ingredient.setPrice(BigDecimal.valueOf(0.1));
		ingredient.setQuantity(BigDecimal.valueOf(1));
		requestDTO.setIngredients(List.of(ingredient));

		HttpEntity<List<CreateItemRequestDTO>> entity = new HttpEntity<>(List.of(requestDTO), createHeadersWithJwt());

		ResponseEntity<List> createResponse = restTemplate.exchange("/api/v1/items", HttpMethod.POST, entity,
				List.class);
		assertEquals(HttpStatus.OK, createResponse.getStatusCode());
		assertNotNull(createResponse.getBody());

	}

	@Test
	@DirtiesContext
	void findAll_shouldReturnAllItems_whenSuccessful() {
		HttpEntity<Void> entity = new HttpEntity<>(createHeadersWithJwt());

		ResponseEntity<List> response = restTemplate.exchange("/api/v1/items", HttpMethod.GET, entity, List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	@DirtiesContext
	void findByCategory_shouldReturnItemsByCategory_whenSuccessful() {
		String category = "FOOD";
		HttpEntity<Void> entity = new HttpEntity<>(createHeadersWithJwt());

		ResponseEntity<List> response = restTemplate.exchange("/api/v1/items/category/" + category, HttpMethod.GET,
				entity, List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	@DirtiesContext
	void updateItem_shouldReturnUpdatedItem_whenSuccessful() {

		CreateItemRequestDTO requestDTO = new CreateItemRequestDTO();
		requestDTO.setName("Test Item");
		requestDTO.setUnit("kg");
		requestDTO.setPrice(BigDecimal.valueOf(10.0));
		requestDTO.setQuantity(BigDecimal.valueOf(2));
		requestDTO.setCategory(ItemCategory.FOOD);
		requestDTO.setDescription("Delicious food item");
		requestDTO.setImageUrl("http://example.com/image.jpg");

		HttpEntity<List<CreateItemRequestDTO>> entity = new HttpEntity<>(List.of(requestDTO), createHeadersWithJwt());
		ResponseEntity<List> createResponse = restTemplate.exchange("/api/v1/items", HttpMethod.POST, entity,
				List.class);
		assertEquals(HttpStatus.OK, createResponse.getStatusCode());
		assertNotNull(createResponse.getBody());

		long id = ((Number) ((Map<String, Object>) createResponse.getBody().get(0)).get("id")).longValue();

		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setName("Updated Test Item");
		itemDTO.setUnit("kg");
		itemDTO.setPrice(BigDecimal.valueOf(15.0));
		itemDTO.setQuantity(BigDecimal.valueOf(3));
		itemDTO.setCategory(ItemCategory.FOOD);
		itemDTO.setDescription("Updated description");
		itemDTO.setImageUrl("http://example.com/new-image.jpg");

		HttpEntity<ItemDTO> itemDTOHttpEntity = new HttpEntity<>(itemDTO, createHeadersWithJwt());
		ResponseEntity<ItemDTO> response = restTemplate.exchange("/api/v1/items/" + id, HttpMethod.PUT,
				itemDTOHttpEntity, ItemDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Updated Test Item", response.getBody().getName());
		assertEquals(BigDecimal.valueOf(15.0), response.getBody().getPrice());
	}

	@Test
	@DirtiesContext
	void deleteItem_shouldReturnNoContent_whenSuccessful() {
		long id = 1L;
		HttpEntity<Void> entity = new HttpEntity<>(createHeadersWithJwt());

		ResponseEntity<Void> response = restTemplate.exchange("/api/v1/items/" + id, HttpMethod.DELETE, entity,
				Void.class);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

}
