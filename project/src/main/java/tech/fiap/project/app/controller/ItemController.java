package tech.fiap.project.app.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.app.service.item.CreateItemService;
import tech.fiap.project.app.service.item.RetrieveItemService;
import tech.fiap.project.app.service.item.UpdateItemService;
import tech.fiap.project.app.service.item.DeleteItemService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/items")
@Validated
@RequiredArgsConstructor
public class ItemController {

	private static final Logger log = LoggerFactory.getLogger(ItemController.class);

	private final CreateItemService createItemService;
	private final RetrieveItemService retrieveItemService;
	private final UpdateItemService updateItemService;
	private final DeleteItemService deleteItemService;

	@PostMapping
	public ResponseEntity<List<ItemDTO>> createItems(@RequestBody @Validated List<ItemDTO> itemDTOs) {
		try {
			log.info("Received request to create items: {}", itemDTOs);
			List<ItemDTO> createdItems = createItemService.createItem(itemDTOs);
			log.info("Items created successfully: {}", createdItems);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdItems);
		}
		catch (Exception e) {
			log.error("Error creating items", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
		}
	}

	@GetMapping
	public ResponseEntity<List<ItemDTO>> findAll() {
		try {
			log.info("Received request to fetch all items");
			List<ItemDTO> items = retrieveItemService.findAll();
			log.info("Fetched items: {}", items);
			return ResponseEntity.ok(items);
		}
		catch (Exception e) {
			log.error("Error fetching items", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
		}
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<ItemDTO>> findByCategory(@PathVariable String category) {
		try {
			log.info("Received request to fetch items by category: {}", category);
			List<ItemDTO> items = retrieveItemService.findByCategory(category);
			log.info("Fetched items: {}", items);
			return ResponseEntity.ok(items);
		}
		catch (Exception e) {
			log.error("Error fetching items", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemDTO> updateItem(@PathVariable Long id, @RequestBody @Validated ItemDTO itemDTO) {
		try {
			log.info("Received request to update item with ID {}: {}", id, itemDTO);
			ItemDTO updatedItem = updateItemService.updateItem(id, itemDTO);
			log.info("Item updated successfully: {}", updatedItem);
			return ResponseEntity.ok(updatedItem);
		} catch (Exception e) {
			log.error("Error updating item", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
		try {
			log.info("Received request to delete item with id {}", id);
			deleteItemService.deleteItem(id);
			log.info("Item deleted successfully");
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			log.error("Error deleting item", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
