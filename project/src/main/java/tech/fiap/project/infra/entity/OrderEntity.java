package tech.fiap.project.infra.entity;

import jakarta.persistence.*;
import lombok.Data;

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

	@OneToMany(fetch = FetchType.EAGER)
	private List<ItemEntity> items;

	@OneToOne
	private PaymentEntity payment;

	@ManyToOne(fetch = FetchType.LAZY)
	private PersonEntity user;

}