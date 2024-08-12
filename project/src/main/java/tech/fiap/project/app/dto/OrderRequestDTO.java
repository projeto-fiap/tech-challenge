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

	private OrderStatus status;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	private List<ItemRequestDTO> items;

	private PaymentDTO payment;

	private PersonDTO person;

	private Duration awaitingTime;

	private BigDecimal totalPrice;

	private KitchenDTO kitchenQueue;

}