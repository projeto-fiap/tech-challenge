package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemMapper {
    public static ItemDTO toDTO(Item item){
        return new ItemDTO(item.getQuantity(),item.getUnit(),item.getName(),convertIngredients(item),item.getItemCategory());
    }

    public static Item toDomain(ItemDTO item){
        return new Item(item.getQuantity(),item.getUnit(),item.getName(),item.getCategory(),convertIngredients(item));
    }

    private static List<Item> convertIngredients(ItemDTO itemDTO){
        List<Item> ingredients;
        if (itemDTO.getIngredients() == null) {
            ingredients= new ArrayList<>();
        }else {
            ingredients = itemDTO.getIngredients().stream().map(ItemMapper::toDomain).toList();
        }
        return ingredients;
    }

    private static List<ItemDTO> convertIngredients(Item item){
        List<ItemDTO> ingredients;
        if (item.getIngredients() == null) {
            ingredients= new ArrayList<>();
        }else {
            ingredients = item.getIngredients().stream().map(ItemMapper::toDTO).toList();
        }
        return ingredients;
    }
}
