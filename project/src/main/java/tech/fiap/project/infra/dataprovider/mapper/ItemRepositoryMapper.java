package tech.fiap.project.infra.dataprovider.mapper;

import tech.fiap.project.app.dto.OrderStatus;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.infra.entity.ItemEntity;
import tech.fiap.project.infra.entity.OrderEntity;

public class ItemRepositoryMapper {

    public static ItemEntity toEntity(Item item) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setQuantity(item.getQuantity());
        itemEntity.setName(item.getName());
        itemEntity.setUnit(item.getUnit());
        itemEntity.setIngredients(item.getIngredients().stream().map(ItemRepositoryMapper::toEntity).toList());
        return itemEntity;
    }

    public static Item toDomain(ItemEntity itemEntity) {
        return new Item(itemEntity.getQuantity(), itemEntity.getUnit(), itemEntity.getName(), itemEntity.getItemCategory(),itemEntity.getIngredients().stream().map(ItemRepositoryMapper::toDomain).toList());
    }
}
