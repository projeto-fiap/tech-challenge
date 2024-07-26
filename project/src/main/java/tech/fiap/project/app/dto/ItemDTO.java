package tech.fiap.project.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor

public class ItemDTO {

	private BigDecimal quantity;

	private String name;

	private String unit;

	private List<ItemDTO> ingredients;

	private ItemCategory category;

}
