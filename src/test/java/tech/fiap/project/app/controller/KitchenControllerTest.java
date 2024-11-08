package tech.fiap.project.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tech.fiap.project.app.dto.KitchenDTO;
import tech.fiap.project.app.service.kitchen.KitchenService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class KitchenControllerTest {

	@Mock
	private KitchenService kitchenService;

	@InjectMocks
	private KitchenController kitchenController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void retrieveOrders_shouldReturnListOfKitchenDTO_whenSuccessful() {
		List<KitchenDTO> kitchenDTOList = List.of(new KitchenDTO());

		when(kitchenService.findAll()).thenReturn(kitchenDTOList);

		ResponseEntity<List<KitchenDTO>> response = kitchenController.retrieveOrders();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(kitchenDTOList, response.getBody());
	}

	@Test
	void retrieveOrderById_shouldReturnKitchenDTO_whenFound() {
		Long id = 1L;
		KitchenDTO kitchenDTO = new KitchenDTO();

		when(kitchenService.findById(id)).thenReturn(Optional.of(kitchenDTO));

		ResponseEntity<KitchenDTO> response = kitchenController.retrieveOrderById(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(kitchenDTO, response.getBody());
	}

	@Test
	void retrieveOrderById_shouldReturnNotFound_whenNotFound() {
		Long id = 1L;

		when(kitchenService.findById(id)).thenReturn(Optional.empty());

		ResponseEntity<KitchenDTO> response = kitchenController.retrieveOrderById(id);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void production_shouldReturnKitchenDTO_whenSuccessful() {
		Long id = 1L;
		KitchenDTO kitchenDTO = new KitchenDTO();

		when(kitchenService.setInProduction(id)).thenReturn(Optional.of(kitchenDTO));

		ResponseEntity<KitchenDTO> response = kitchenController.production(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(kitchenDTO, response.getBody());
	}

	@Test
	void production_shouldReturnNotFound_whenNotFound() {
		Long id = 1L;

		when(kitchenService.setInProduction(id)).thenReturn(Optional.empty());

		ResponseEntity<KitchenDTO> response = kitchenController.production(id);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void done_shouldReturnKitchenDTO_whenSuccessful() {
		Long id = 1L;
		KitchenDTO kitchenDTO = new KitchenDTO();

		when(kitchenService.setDone(id)).thenReturn(Optional.of(kitchenDTO));

		ResponseEntity<KitchenDTO> response = kitchenController.done(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(kitchenDTO, response.getBody());
	}

	@Test
	void done_shouldReturnNotFound_whenNotFound() {
		Long id = 1L;

		when(kitchenService.setDone(id)).thenReturn(Optional.empty());

		ResponseEntity<KitchenDTO> response = kitchenController.done(id);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

}