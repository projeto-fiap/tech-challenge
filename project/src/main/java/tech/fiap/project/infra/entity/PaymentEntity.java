package tech.fiap.project.infra.entity;

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
}
