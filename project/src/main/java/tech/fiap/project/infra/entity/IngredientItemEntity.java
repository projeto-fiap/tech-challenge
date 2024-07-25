package tech.fiap.project.infra.entity;

import jakarta.persistence.*;
import lombok.Data;
import tech.fiap.project.app.dto.IngredientCategory;

import java.math.BigDecimal;
import java.util.Currency;

@Entity
@Table(name = "ingredient_item")
@Data
public class IngredientItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    private IngredientCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;


    private String currency;
}
