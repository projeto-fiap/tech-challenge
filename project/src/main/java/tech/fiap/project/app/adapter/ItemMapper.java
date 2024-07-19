package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.infra.entity.ItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {

    // Converte Item para ItemDTO
    public static ItemDTO toDTO(Item item) {
        return new ItemDTO(
                item.getQuantity(),
                item.getName(),
                item.getUnit(),
                convertItemListToDTO(item.getIngredients()),
                item.getItemCategory()
        );
    }

    // Converte ItemDTO para Item
    public static Item toDomain(ItemDTO itemDTO) {
        return new Item(
                itemDTO.getQuantity(),
                itemDTO.getName(),
                itemDTO.getUnit(),
                itemDTO.getCategory(),
                convertItemDTOListToDomain(itemDTO.getIngredients())
        );
    }

    // Converte Item para ItemEntity
    public static ItemEntity toEntity(Item item) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setName(item.getName());
        itemEntity.setQuantity(item.getQuantity());
        itemEntity.setUnit(item.getUnit());
        itemEntity.setItemCategory(item.getItemCategory());
        itemEntity.setIngredients(convertItemListToEntity(item.getIngredients()));
        return itemEntity;
    }

    // Converte ItemEntity para Item
    public static Item toDomain(ItemEntity itemEntity) {
        return new Item(
                itemEntity.getQuantity(),
                itemEntity.getName(),
                itemEntity.getUnit(),
                itemEntity.getItemCategory(),
                convertItemEntityListToDomain(itemEntity.getIngredients())
        );
    }

    // Converte lista de ItemDTO para lista de Item
    private static List<Item> convertItemDTOListToDomain(List<ItemDTO> itemDTOs) {
        if (itemDTOs == null) {
            return new ArrayList<>();
        }
        return itemDTOs.stream()
                .map(ItemMapper::toDomain)
                .collect(Collectors.toList());
    }

    // Converte lista de Item para lista de ItemDTO
    private static List<ItemDTO> convertItemListToDTO(List<Item> items) {
        if (items == null) {
            return new ArrayList<>();
        }
        return items.stream()
                .map(ItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Converte lista de Item para lista de ItemEntity
    private static List<ItemEntity> convertItemListToEntity(List<Item> items) {
        if (items == null) {
            return new ArrayList<>();
        }
        return items.stream()
                .map(ItemMapper::toEntity)
                .collect(Collectors.toList());
    }

    // Converte lista de ItemEntity para lista de Item
    private static List<Item> convertItemEntityListToDomain(List<ItemEntity> itemEntities) {
        if (itemEntities == null) {
            return new ArrayList<>();
        }
        return itemEntities.stream()
                .map(ItemMapper::toDomain)
                .collect(Collectors.toList());
    }
}
