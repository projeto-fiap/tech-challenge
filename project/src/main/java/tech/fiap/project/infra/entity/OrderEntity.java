package tech.fiap.project.infra.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "\"order\"")
@Data
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String status;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<ItemEntity> items;

	@OneToOne(cascade = CascadeType.ALL)
	private PaymentEntity payment;

	@ManyToOne(fetch = FetchType.LAZY)
	private PersonEntity person;

	private Duration awaitingTime;

	private BigDecimal totalPrice;

}