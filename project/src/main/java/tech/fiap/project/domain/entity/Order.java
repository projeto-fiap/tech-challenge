package tech.fiap.project.domain.entity;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

	public Order(Long id, OrderStatus status, LocalDateTime createdDate, LocalDateTime updatedDate, List<Item> items,
			List<Payment> payments, Duration awaitingTime, Person person, BigDecimal totalPrice) {
		this.id = id;
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.items = items;
		this.payments = payments;
		this.awaitingTime = awaitingTime;
		this.person = person;
		this.totalPrice = totalPrice;
	}

	private Long id;

	private OrderStatus status;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	private List<Item> items;

	private List<Payment> payments;

	private Duration awaitingTime;

	private Person person;

	private BigDecimal totalPrice;

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Duration getAwaitingTime() {
		return awaitingTime;
	}

	public void setAwaitingTime(Duration awaitingTime) {
		this.awaitingTime = awaitingTime;
	}

}