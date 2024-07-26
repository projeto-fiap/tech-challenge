package tech.fiap.project.infra.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "item")
@Data
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private BigDecimal quantity;

	private String unit;

	@Enumerated(EnumType.STRING)
	private ItemCategory itemCategory;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ItemEntity> ingredients;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientItemEntity> ingredients;
}
