package tech.fiap.project.infra.entity;

import jakarta.persistence.*;
import lombok.Data;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.KitchenStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "kitchen")
@Data
public class KitchenEntity {

	@Id
	@JoinColumn(name = "order_id")
	private Long orderId;

	@JoinColumn(name = "creation_date")
	private LocalDateTime creationDate;

	@JoinColumn(name = "updated_date")
	private LocalDateTime updatedDate;

	@Enumerated(EnumType.STRING)
	private KitchenStatus status;

}
