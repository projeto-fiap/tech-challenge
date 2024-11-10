package tech.fiap.project.infra.mapper;

import org.junit.jupiter.api.Test;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.infra.entity.ItemCategory;
import tech.fiap.project.infra.entity.ItemEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ItemRepositoryMapperTest {

	@Test
	void toEntity_shouldMapItemToItemEntity() {
		Item item = new Item(1L, "Item Name", BigDecimal.valueOf(10.0), BigDecimal.ONE, "Unit", ItemCategory.FOOD,
				List.of(), "Description", "ImageUrl");
		ItemEntity itemEntity = ItemRepositoryMapper.toEntity(item);

		assertEquals(item.getId(), itemEntity.getId());
		assertEquals(item.getName(), itemEntity.getName());
		assertEquals(item.getPrice(), itemEntity.getPrice());
		assertEquals(item.getQuantity(), itemEntity.getQuantity());
		assertEquals(item.getUnit(), itemEntity.getUnit());
		assertEquals(item.getItemCategory(), itemEntity.getItemCategory());
		assertEquals(item.getIngredients().size(), itemEntity.getIngredients().size());
		assertEquals(item.getDescription(), itemEntity.getDescription());
		assertEquals(item.getImageUrl(), itemEntity.getImageUrl());
	}

	@Test
	void toEntity_shouldReturnNullWhenItemIsNull() {
		ItemEntity itemEntity = ItemRepositoryMapper.toEntity(null);
		assertNull(itemEntity);
	}

	@Test
	void toDomain_shouldMapItemEntityToItem() {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setId(1L);
		itemEntity.setName("Item Name");
		itemEntity.setPrice(BigDecimal.valueOf(10.0));
		itemEntity.setQuantity(BigDecimal.valueOf(2));
		itemEntity.setUnit("Unit");
		itemEntity.setItemCategory(ItemCategory.FOOD);
		itemEntity.setIngredients(List.of());
		itemEntity.setDescription("Description");
		itemEntity.setImageUrl("ImageUrl");

		Item item = ItemRepositoryMapper.toDomain(itemEntity);

		assertEquals(itemEntity.getId(), item.getId());
		assertEquals(itemEntity.getName(), item.getName());
		assertEquals(itemEntity.getPrice(), item.getPrice());
		assertEquals(itemEntity.getQuantity(), item.getQuantity());
		assertEquals(itemEntity.getUnit(), item.getUnit());
		assertEquals(itemEntity.getItemCategory(), item.getItemCategory());
		assertEquals(itemEntity.getIngredients().size(), item.getIngredients().size());
		assertEquals(itemEntity.getDescription(), item.getDescription());
		assertEquals(itemEntity.getImageUrl(), item.getImageUrl());
	}

	@Test
	void toDomain_shouldReturnNullWhenItemEntityIsNull() {
		Item item = ItemRepositoryMapper.toDomain(null);
		assertNull(item);
	}

}