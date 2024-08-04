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
public class OrderDTO {

	private Long id;

	private OrderStatus status;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	private List<ItemDTO> items;

	private PaymentDTO payment;

	private PersonDTO person;

	private Duration awaitingTime;

	private BigDecimal totalPrice;

}