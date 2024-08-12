package tech.fiap.project.app.dto;

import lombok.Getter;
import lombok.Setter;
import tech.fiap.project.domain.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {

	private Long id;

	private LocalDateTime createdDate;

	private List<ItemRequestDTO> items;

	private PersonDTO person;

	private Duration awaitingTime;

}