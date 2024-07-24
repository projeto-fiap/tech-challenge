package tech.fiap.project.app.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.usecase.CreateItem;
import tech.fiap.project.domain.usecase.GetAllItems;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/items")
@Validated
@RequiredArgsConstructor
public class ItemController {

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    private final CreateItem createItemUseCase;
    private final GetAllItems getAllItems;

    @PostMapping
    public ResponseEntity<List<ItemDTO>> createItems(@RequestBody @Validated List<ItemDTO> itemDTOs) {
        try {
            log.info("Received request to create items: {}", itemDTOs);
            List<ItemDTO> createdItems = createItemUseCase.createItems(itemDTOs);
            log.info("Items created successfully: {}", createdItems);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdItems);
        } catch (Exception e) {
            log.error("Error creating items", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        try {
            log.info("Received request to fetch all items");
            List<ItemDTO> items = getAllItems.getAllItems();
            log.info("Fetched items: {}", items);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            log.error("Error fetching items", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
