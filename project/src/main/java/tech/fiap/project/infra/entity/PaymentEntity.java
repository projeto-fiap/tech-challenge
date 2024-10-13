package tech.fiap.project.infra.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import tech.fiap.project.app.dto.StatePayment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Table(name = "payment")
@Data
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime paymentDate;

	private String paymentMethod;

	private BigDecimal amount;

	private Currency currency;

	private StatePayment state;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "order_payments", joinColumns = @JoinColumn(name = "payment_id"),
			inverseJoinColumns = @JoinColumn(name = "order_id"))
	@JsonManagedReference
	private OrderEntity order;

}
