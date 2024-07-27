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

	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	private ItemCategory itemCategory;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
	private List<ItemEntity> ingredients;

}
