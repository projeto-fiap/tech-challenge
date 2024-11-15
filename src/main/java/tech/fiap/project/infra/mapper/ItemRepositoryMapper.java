package tech.fiap.project.infra.mapper;

import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.infra.entity.ItemEntity;

import java.util.List;

public class ItemRepositoryMapper {

	private ItemRepositoryMapper() {

	}

	public static ItemEntity toEntity(Item item) {
		if (item == null) {
			return null;
		}
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setId(item.getId());
		itemEntity.setQuantity(item.getQuantity());
		itemEntity.setName(item.getName());
		itemEntity.setUnit(item.getUnit());
		itemEntity.setIngredients(item.getIngredients().stream().map(ItemRepositoryMapper::toEntity).toList());
		itemEntity.setItemCategory(item.getItemCategory());
		itemEntity.setPrice(item.getPrice());
		itemEntity.setDescription(item.getDescription());
		itemEntity.setImageUrl(item.getImageUrl());
		return itemEntity;
	}

	public static Item toDomain(ItemEntity itemEntity) {
		if (itemEntity == null) {
			return null;
		}
		List<ItemEntity> ingredients = itemEntity.getIngredients();
		if (ingredients == null) {
			ingredients = List.of();
		}
		return new Item(itemEntity.getId(), itemEntity.getName(), itemEntity.getPrice(), itemEntity.getQuantity(),
				itemEntity.getUnit(), itemEntity.getItemCategory(),
				ingredients.stream().map(ItemRepositoryMapper::toDomain).toList(), itemEntity.getDescription(),
				itemEntity.getImageUrl());
	}

}
