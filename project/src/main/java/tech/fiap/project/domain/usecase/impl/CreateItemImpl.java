package tech.fiap.project.domain.usecase.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import tech.fiap.project.app.adapter.ItemRepositoryMapper;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.usecase.CreateItem;
import tech.fiap.project.infra.entity.ItemEntity;
import tech.fiap.project.infra.repository.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateItemImpl implements CreateItem {

    private final ItemRepository itemRepository;

    public CreateItemImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public List<ItemDTO> createItems(List<ItemDTO> itemDTOs) {
        try {
            // Convert DTOs to entities
            List<ItemEntity> itemEntities = itemDTOs.stream()
                    .map(ItemRepositoryMapper::toEntity)
                    .collect(Collectors.toList());

            // Ensure each ingredient is associated with the correct item
            itemEntities.forEach(itemEntity -> {
                if (itemEntity.getIngredients() != null) {
                    itemEntity.getIngredients().forEach(ingredient -> ingredient.setItem(itemEntity));
                }
            });

            // Save items (and their ingredients) in a single transaction
            List<ItemEntity> savedItemEntities = itemRepository.saveAll(itemEntities);

            // Convert saved entities back to DTOs
            return savedItemEntities.stream()
                    .map(ItemRepositoryMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error while creating items", e);
        }
    }
}
