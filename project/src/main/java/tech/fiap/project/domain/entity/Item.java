package tech.fiap.project.domain.entity;

import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.List;

public class Item {

	private Long id;

	private String name;

	private BigDecimal price;

	private BigDecimal quantity;

	private String unit;

	private ItemCategory itemCategory;

	private List<Item> ingredients;

	private String description;

	private String imageUrl;

	public Item(Long id, String name, BigDecimal price, BigDecimal quantity, String unit, ItemCategory itemCategory, List<Item> ingredients, String description, String imageUrl) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.unit = unit;
		this.itemCategory = itemCategory;
		this.ingredients = ingredients;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public ItemCategory getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}

	public List<Item> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Item> ingredients) {
		this.ingredients = ingredients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
