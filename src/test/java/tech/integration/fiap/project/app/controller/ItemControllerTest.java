package tech.integration.fiap.project.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import tech.fiap.project.app.dto.CreateItemRequestDTO;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.infra.configuration.Configuration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {Configuration.class, ServletWebServerFactoryAutoConfiguration.class})
@ActiveProfiles("integration-test")
class ItemControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void deleteThrowsUnathorized() {
        Long id = 1L;
        restTemplate.delete("/api/v1/items/" + id);
        ResponseEntity<Void> response = restTemplate.getForEntity("/api/v1/items/" + id, Void.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void createItems_shouldReturnCreatedItems_whenSuccessful() {
        CreateItemRequestDTO requestDTO = new CreateItemRequestDTO();
        // Set necessary fields for requestDTO

        ResponseEntity<List> response = restTemplate.withBasicAuth("teste@fiap.com","developer").postForEntity("/api/v1/items", List.of(requestDTO), List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void findAll_shouldReturnAllItems_whenSuccessful() {
        ResponseEntity<List> response = restTemplate.getForEntity("/api/v1/items", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void findByCategory_shouldReturnItemsByCategory_whenSuccessful() {
        String category = "category";
        ResponseEntity<List> response = restTemplate.getForEntity("/api/v1/items/category/" + category, List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void updateItem_shouldReturnUpdatedItem_whenSuccessful() {
        Long id = 1L;
        ItemDTO itemDTO = new ItemDTO();
        // Set necessary fields for itemDTO

        restTemplate.put("/api/v1/items/" + id, itemDTO);
        ResponseEntity<ItemDTO> response = restTemplate.getForEntity("/api/v1/items/" + id, ItemDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void deleteItem_shouldReturnNoContent_whenSuccessful() {
        Long id = 1L;

        restTemplate.delete("/api/v1/items/" + id);
        ResponseEntity<Void> response = restTemplate.getForEntity("/api/v1/items/" + id, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}