package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemMapper {

	public static ItemDTO toDTO(Item item) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setId(item.getId());
		itemDTO.setQuantity(item.getQuantity());
		itemDTO.setUnit(item.getUnit());
		itemDTO.setName(item.getName());
		itemDTO.setPrice(item.getPrice());
		itemDTO.setIngredients(convertIngredients(item));
		itemDTO.setCategory(item.getItemCategory());
		return itemDTO;
	}

	public static Item toDomain(ItemDTO item) {
		return new Item(item.getId(), item.getName(),item.getPrice(), item.getQuantity(), item.getUnit(), item.getCategory(),	convertIngredients(item));
	}

	private static List<Item> convertIngredients(ItemDTO itemDTO) {
		List<Item> ingredients;
		if (itemDTO.getIngredients() == null) {
			ingredients = new ArrayList<>();
		}
		else {
			ingredients = itemDTO.getIngredients().stream().map(ItemMapper::toDomain).toList();
		}
		return ingredients;
	}

	private static List<ItemDTO> convertIngredients(Item item) {
		List<ItemDTO> ingredients;
		if (item.getIngredients() == null) {
			ingredients = new ArrayList<>();
		}
		else {
			ingredients = item.getIngredients().stream().map(ItemMapper::toDTO).toList();
		}
		return ingredients;
	}

}
