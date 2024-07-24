package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.IngredientItemDTO;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.infra.entity.IngredientItemEntity;
import tech.fiap.project.infra.entity.ItemEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ItemRepositoryMapper {

    public static ItemEntity toEntity(ItemDTO itemDTO) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(itemDTO.getId());
        itemEntity.setName(itemDTO.getName());
        itemEntity.setPrice(itemDTO.getPrice());
        itemEntity.setQuantity(itemDTO.getQuantity());
        itemEntity.setUnit(itemDTO.getUnit());
        itemEntity.setItemCategory(itemDTO.getItemCategory());
        itemEntity.setIngredients(mapIngredientsToEntity(itemDTO.getIngredients()));
        return itemEntity;
    }

    public static ItemDTO toDTO(ItemEntity itemEntity) {
        return new ItemDTO(
                itemEntity.getId(),
                itemEntity.getName(),
                itemEntity.getPrice(),
                itemEntity.getQuantity(),
                itemEntity.getUnit(),
                itemEntity.getItemCategory(),
                itemEntity.getIngredients().stream()
                        .map(ingredient -> new IngredientItemDTO(
                                ingredient.getId(),
                                ingredient.getName(),
                                ingredient.getDescription(),
                                ingredient.getImageUrl(),
                                ingredient.getPrice(),
                                ingredient.getCurrency(),
                                ingredient.getCategory(),
                                ingredient.getQuantity()
                        )).collect(Collectors.toList())
        );
    }

    public static List<IngredientItemEntity> mapIngredientsToEntity(List<IngredientItemDTO> ingredients) {
        return ingredients == null ? List.of() :
                ingredients.stream()
                        .map(ingredient -> {
                            IngredientItemEntity entity = new IngredientItemEntity();
                            entity.setId(ingredient.getId());  // Adicionado setId
                            entity.setName(ingredient.getName());
                            entity.setDescription(ingredient.getDescription());
                            entity.setImageUrl(ingredient.getImageUrl());
                            entity.setPrice(ingredient.getPrice());
                            entity.setCurrency(ingredient.getCurrency());
                            entity.setCategory(ingredient.getCategory());
                            entity.setQuantity(ingredient.getQuantity());
                            return entity;
                        })
                        .collect(Collectors.toList());
    }

    private static List<IngredientItemDTO> mapIngredientsToDTO(List<IngredientItemEntity> entities) {
        return entities == null ? List.of() :
                entities.stream()
                        .map(entity -> new IngredientItemDTO(
                                entity.getId(),
                                entity.getName(),
                                entity.getDescription(),
                                entity.getImageUrl(),
                                entity.getPrice(),
                                entity.getCurrency(),
                                entity.getCategory(),
                                entity.getQuantity()
                        ))
                        .collect(Collectors.toList());
    }
}
