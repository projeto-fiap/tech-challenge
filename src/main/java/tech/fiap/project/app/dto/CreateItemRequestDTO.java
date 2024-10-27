package tech.fiap.project.app.dto;

import lombok.Data;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateItemRequestDTO {

	private BigDecimal quantity;

	private String name;

	private String unit;

	private BigDecimal price;

	private List<CreateItemRequestDTO> ingredients;

	private ItemCategory category;

	private String description;

	private String imageUrl;

}
