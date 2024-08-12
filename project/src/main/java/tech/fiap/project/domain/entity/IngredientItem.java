package tech.fiap.project.domain.entity;

import tech.fiap.project.app.dto.IngredientCategory;

import java.math.BigDecimal;
import java.util.Currency;

public class IngredientItem {

	public IngredientItem(String name, String description, String imageUrl, BigDecimal price, Currency currency,
			IngredientCategory category, Long quantity) {
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
		this.currency = currency;
		this.category = category;
		this.quantity = quantity;
	}

	private String name;

	private String description;

	private String imageUrl;

	private BigDecimal price;

	private Currency currency;

	private IngredientCategory category;

	private Long quantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public IngredientCategory getCategory() {
		return category;
	}

	public void setCategory(IngredientCategory category) {
		this.category = category;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
