package tech.fiap.project.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor

public class ItemDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal quantity;
    private String unit;
    private ItemCategory itemCategory;
    private List<IngredientItemDTO> ingredients;


    public ItemDTO() {

    }
}
