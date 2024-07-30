package tech.fiap.project.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public class Payment {

	private LocalDateTime paymentDate;

	private String paymentMethod;

	private BigDecimal amount;

	private Currency currency;
	private Order order;

	public Payment(LocalDateTime paymentDate, String paymentMethod, BigDecimal amount, Currency currency) {
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
		this.amount = amount;
		this.currency = currency;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}
