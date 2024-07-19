package tech.fiap.project.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.adapter.ItemMapper;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.usecase.CreateItem;
import tech.fiap.project.infra.entity.ItemEntity;
import tech.fiap.project.infra.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/items")
@Validated
@RequiredArgsConstructor
public class ItemController {
    @Autowired
    private final CreateItem createItemUseCase;
    @Autowired
    private ItemRepository itemRepository;

    @PostMapping
    public ResponseEntity<List<ItemDTO>> createItems(@RequestBody @Validated List<ItemDTO> itemDTOs) {
        List<ItemDTO> createdItems = createItemUseCase.createItems(itemDTOs);
        return ResponseEntity.ok(createdItems);
    }


    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<ItemEntity> itemEntities = itemRepository.findAll();
        List<ItemDTO> itemDTOs = itemEntities.stream()
                .map(itemEntity -> ItemMapper.toDTO(ItemMapper.toDomain(itemEntity)))
                .collect(Collectors.toList());
        return ResponseEntity.ok(itemDTOs);
    }
}
