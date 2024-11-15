package tech.fiap.project.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Kitchen {

	private Long orderId;

	private LocalDateTime creationDate;

	private LocalDateTime updatedDate;

	private KitchenStatus status;

	public Kitchen(Long orderId, LocalDateTime creationDate, LocalDateTime updatedDate, KitchenStatus status) {
		this.orderId = orderId;
		this.creationDate = creationDate;
		this.updatedDate = updatedDate;
		this.status = status;
	}

}
