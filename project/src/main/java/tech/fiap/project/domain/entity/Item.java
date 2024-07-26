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
    private List<IngredientItem> ingredients;



    public Item(String name, BigDecimal price, BigDecimal quantity, String unit, ItemCategory itemCategory, List<IngredientItem> ingredients) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.itemCategory = itemCategory;
        this.ingredients = ingredients;
    }

    public Item(Long id, String name, BigDecimal price, BigDecimal quantity, String unit, ItemCategory itemCategory, List<IngredientItem> collect) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

	private ItemCategory itemCategory;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

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

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public List<IngredientItem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientItem> ingredients) {
        this.ingredients = ingredients;
    }
}
