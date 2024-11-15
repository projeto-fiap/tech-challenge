package tech.fiap.project.app.dto;

import lombok.Data;
import tech.fiap.project.domain.entity.KitchenStatus;

import java.time.LocalDateTime;

@Data
public class KitchenDTO {

	private Long orderId;

	private LocalDateTime creationDate;

	private LocalDateTime updatedDate;

	private KitchenStatus status;

}
