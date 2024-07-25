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

    public List<IngredientItem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientItem> ingredients) {
        this.ingredients = ingredients;
    }
}
