package tech.fiap.project.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientItemDTO {

	private Long id;

	private String name;

	private String description;

	private String imageUrl;

	private BigDecimal price;

	private String currency;

	private IngredientCategory category;

	private BigDecimal quantity;

}
