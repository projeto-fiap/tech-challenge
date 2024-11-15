package tech.fiap.project.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tech.fiap.project.app.dto.StatePayment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Setter
@Getter
@AllArgsConstructor
public class Payment {

	private LocalDateTime paymentDate;

	private String paymentMethod;

	private BigDecimal amount;

	private Currency currency;

	private Order order;

	private StatePayment state;

}
