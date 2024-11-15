package tech.fiap.project.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
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

}
