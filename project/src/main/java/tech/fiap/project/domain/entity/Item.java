package tech.fiap.project.domain.entity;

import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.List;

public class Item {

	private BigDecimal quantity;

	private String name;

	private String unit;

	private ItemCategory itemCategory;

	private List<Item> ingredients;

	public Item(BigDecimal quantity, String name, String unit, ItemCategory itemCategory, List<Item> ingredients) {
		this.quantity = quantity;
		this.name = name;
		this.unit = unit;
		this.itemCategory = itemCategory;
		this.ingredients = ingredients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<Item> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Item> ingredients) {
		this.ingredients = ingredients;
	}

	public ItemCategory getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}

}
