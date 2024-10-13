package tech.fiap.project.app.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import tech.fiap.project.domain.entity.KitchenStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
public class KitchenDTO {

	private Long orderId;

	private LocalDateTime creationDate;

	private LocalDateTime updatedDate;

	private KitchenStatus status;

}
