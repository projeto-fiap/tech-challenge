package tech.fiap.project.domain.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public class Kitchen {

	private Long orderId;

	private LocalDateTime creationDate;

	private LocalDateTime updatedDate;

	private KitchenStatus status;

	public Kitchen(Long orderId, LocalDateTime creationDate, KitchenStatus status) {
		this.orderId = orderId;
		this.creationDate = creationDate;
		this.updatedDate = creationDate;
		this.status = status;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public KitchenStatus getStatus() {
		return status;
	}

	public void setStatus(KitchenStatus status) {
		this.status = status;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

}
