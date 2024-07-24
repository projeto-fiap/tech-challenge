package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.IngredientItemDTO;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.entity.IngredientItem;
import tech.fiap.project.domain.entity.Item;

import java.util.stream.Collectors;

public class ItemMapper {

    public static Item toDomain(ItemDTO itemDTO) {
        return new Item(
                itemDTO.getId(),
                itemDTO.getName(),
                itemDTO.getPrice(),
                itemDTO.getQuantity(),
                itemDTO.getUnit(),
                itemDTO.getItemCategory(),
                itemDTO.getIngredients().stream()
                        .map(dto -> new IngredientItem(
                                dto.getId(),
                                dto.getName(),
                                dto.getDescription(),
                                dto.getImageUrl(),
                                dto.getPrice(),
                                dto.getCurrency(),
                                dto.getCategory(),
                                dto.getQuantity()
                        )).collect(Collectors.toList())
        );
    }

    public static ItemDTO toDTO(Item item) {
        return new ItemDTO(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getQuantity(),
                item.getUnit(),
                item.getItemCategory(),
                item.getIngredients().stream()
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
}
