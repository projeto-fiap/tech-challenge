package tech.fiap.project.infra.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_payments",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "payment_id"))
	@JsonBackReference
	private List<PaymentEntity> payments;

	@ManyToOne(fetch = FetchType.LAZY)
	private PersonEntity person;

	private Duration awaitingTime;

	private BigDecimal totalPrice;

}