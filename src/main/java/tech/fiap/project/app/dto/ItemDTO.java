package tech.fiap.project.app.dto;

import lombok.*;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class ItemDTO {

	private Long id;

	private BigDecimal quantity;

	private String name;

	private String unit;

	private BigDecimal price;

	private List<ItemDTO> ingredients;

	private ItemCategory category;

	private String description;

	private String imageUrl;

}
