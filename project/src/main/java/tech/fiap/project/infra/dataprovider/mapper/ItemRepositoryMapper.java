package tech.fiap.project.infra.dataprovider.mapper;

import tech.fiap.project.domain.entity.IngredientItem;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.infra.entity.ItemEntity;
import tech.fiap.project.infra.entity.IngredientItemEntity;

import java.util.stream.Collectors;

public class ItemRepositoryMapper {

    public static ItemEntity toEntity(Item item) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setName(item.getName());
        itemEntity.setPrice(item.getPrice());
        itemEntity.setQuantity(item.getQuantity());
        itemEntity.setUnit(item.getUnit());
        itemEntity.setItemCategory(item.getItemCategory());
        itemEntity.setIngredients(item.getIngredients().stream()
                .map(ItemRepositoryMapper::ingredientToEntity)
                .collect(Collectors.toList()));
        return itemEntity;
    }

    public static Item toDomain(ItemEntity itemEntity) {
        return new Item(
                itemEntity.getName(),
                itemEntity.getPrice(),
                itemEntity.getQuantity(),
                itemEntity.getUnit(),
                itemEntity.getItemCategory(),
                itemEntity.getIngredients().stream()
                        .map(ItemRepositoryMapper::ingredientToDomain)
                        .collect(Collectors.toList())
        );
    }

    private static IngredientItemEntity ingredientToEntity(IngredientItem ingredient) {
        IngredientItemEntity entity = new IngredientItemEntity();
        entity.setName(ingredient.getName());
        entity.setDescription(ingredient.getDescription());
        entity.setImageUrl(ingredient.getImageUrl());
        entity.setPrice(ingredient.getPrice());
        entity.setCurrency(String.valueOf(ingredient.getCurrency()));
        entity.setCategory(ingredient.getCategory());
        entity.setQuantity(ingredient.getQuantity());
        return entity;
    }

    private static IngredientItem ingredientToDomain(IngredientItemEntity entity) {
        return new IngredientItem(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getPrice(),
                entity.getCurrency(),
                entity.getCategory(),
                entity.getQuantity()
        );
    }
}
