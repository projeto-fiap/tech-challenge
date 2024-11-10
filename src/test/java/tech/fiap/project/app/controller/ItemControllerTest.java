package tech.fiap.project.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tech.fiap.project.app.dto.CreateItemRequestDTO;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.app.service.item.CreateItemService;
import tech.fiap.project.app.service.item.RetrieveItemService;
import tech.fiap.project.app.service.item.UpdateItemService;
import tech.fiap.project.app.service.item.DeleteItemService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ItemControllerTest {

	@Mock
	private CreateItemService createItemService;

	@Mock
	private RetrieveItemService retrieveItemService;

	@Mock
	private UpdateItemService updateItemService;

	@Mock
	private DeleteItemService deleteItemService;

	@InjectMocks
	private ItemController itemController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void createItems_shouldReturnCreatedItems_whenSuccessful() {
		List<CreateItemRequestDTO> requestDTOs = List.of(new CreateItemRequestDTO());
		List<ItemDTO> responseDTOs = List.of(new ItemDTO());

		when(createItemService.createItem(requestDTOs)).thenReturn(responseDTOs);

		ResponseEntity<List<ItemDTO>> response = itemController.createItems(requestDTOs);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(responseDTOs, response.getBody());
	}

	@Test
	void createItems_shouldReturnInternalServerError_whenExceptionThrown() {
		List<CreateItemRequestDTO> requestDTOs = List.of(new CreateItemRequestDTO());

		when(createItemService.createItem(requestDTOs)).thenThrow(new RuntimeException("Error"));

		ResponseEntity<List<ItemDTO>> response = itemController.createItems(requestDTOs);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals(Collections.emptyList(), response.getBody());
	}

	@Test
	void findAll_shouldReturnAllItems_whenSuccessful() {
		List<ItemDTO> items = List.of(new ItemDTO());

		when(retrieveItemService.findAll()).thenReturn(items);

		ResponseEntity<List<ItemDTO>> response = itemController.findAll();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(items, response.getBody());
	}

	@Test
	void findAll_shouldReturnInternalServerError_whenExceptionThrown() {
		when(retrieveItemService.findAll()).thenThrow(new RuntimeException("Error"));

		ResponseEntity<List<ItemDTO>> response = itemController.findAll();

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals(Collections.emptyList(), response.getBody());
	}

	@Test
	void findByCategory_shouldReturnItemsByCategory_whenSuccessful() {
		String category = "category";
		List<ItemDTO> items = List.of(new ItemDTO());

		when(retrieveItemService.findByCategory(category)).thenReturn(items);

		ResponseEntity<List<ItemDTO>> response = itemController.findByCategory(category);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(items, response.getBody());
	}

	@Test
	void findByCategory_shouldReturnInternalServerError_whenExceptionThrown() {
		String category = "category";

		when(retrieveItemService.findByCategory(category)).thenThrow(new RuntimeException("Error"));

		ResponseEntity<List<ItemDTO>> response = itemController.findByCategory(category);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals(Collections.emptyList(), response.getBody());
	}

	@Test
	void updateItem_shouldReturnUpdatedItem_whenSuccessful() {
		Long id = 1L;
		ItemDTO itemDTO = new ItemDTO();

		when(updateItemService.updateItem(id, itemDTO)).thenReturn(itemDTO);

		ResponseEntity<ItemDTO> response = itemController.updateItem(id, itemDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(itemDTO, response.getBody());
	}

	@Test
	void updateItem_shouldReturnInternalServerError_whenExceptionThrown() {
		Long id = 1L;
		ItemDTO itemDTO = new ItemDTO();

		when(updateItemService.updateItem(id, itemDTO)).thenThrow(new RuntimeException("Error"));

		ResponseEntity<ItemDTO> response = itemController.updateItem(id, itemDTO);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	@Test
	void deleteItem_shouldReturnNoContent_whenSuccessful() {
		Long id = 1L;

		ResponseEntity<Void> response = itemController.deleteItem(id);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void deleteItem_shouldReturnInternalServerError_whenExceptionThrown() {
		Long id = 1L;

		doThrow(new RuntimeException("Error")).when(deleteItemService).deleteItem(id);

		ResponseEntity<Void> response = itemController.deleteItem(id);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

}