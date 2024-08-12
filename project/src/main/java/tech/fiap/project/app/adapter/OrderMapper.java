package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.*;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.Person;

import java.time.LocalDateTime;
import java.util.List;

public class OrderMapper {

	public static List<OrderResponseDTO> toDTO(List<Order> orders) {
		return orders.stream().map(OrderMapper::toDTO).toList();
	}

	public static OrderResponseDTO toDTO(Order order) {
		PersonDTO person = null;
		if (order.getPerson() != null) {
			person = PersonMapper.toDTO(order.getPerson());
		}
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		orderResponseDTO.setId(order.getId());
		orderResponseDTO.setStatus(order.getStatus());
		orderResponseDTO.setCreatedDate(order.getCreatedDate());
		;
		orderResponseDTO.setUpdatedDate(order.getUpdatedDate());
		orderResponseDTO.setItems(order.getItems().stream().map(item -> {
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setId(item.getId());
			itemDTO.setQuantity(item.getQuantity());
			itemDTO.setUnit(item.getUnit());
			itemDTO.setPrice(item.getPrice());
			itemDTO.setName(item.getName());
			itemDTO.setCategory(item.getItemCategory());
			itemDTO.setIngredients(item.getIngredients().stream().map(ItemMapper::toDTO).toList());
			itemDTO.setDescription(item.getDescription());
			itemDTO.setImageUrl(item.getImageUrl());
			return itemDTO;
		}).toList());
		orderResponseDTO.setPayment(PaymentMapper.toDomain(order.getPayment()));
		orderResponseDTO.setPerson(person);
		orderResponseDTO.setAwaitingTime(order.getAwaitingTime());
		orderResponseDTO.setTotalPrice(order.getTotalPrice());
		return orderResponseDTO;
	}

	public static OrderResponseDTO toResponse(Order order) {
		PersonDTO person = null;
		if (order.getPerson() != null) {
			person = PersonMapper.toDTO(order.getPerson());
		}
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		orderResponseDTO.setId(order.getId());
		orderResponseDTO.setStatus(order.getStatus());
		orderResponseDTO.setCreatedDate(order.getCreatedDate());
		;
		orderResponseDTO.setUpdatedDate(order.getUpdatedDate());
		orderResponseDTO.setItems(order.getItems().stream().map(item -> {
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setId(item.getId());
			itemDTO.setQuantity(item.getQuantity());
			itemDTO.setUnit(item.getUnit());
			itemDTO.setPrice(item.getPrice());
			itemDTO.setName(item.getName());
			itemDTO.setCategory(item.getItemCategory());
			itemDTO.setIngredients(item.getIngredients().stream().map(ItemMapper::toDTO).toList());
			itemDTO.setDescription(item.getDescription());
			itemDTO.setImageUrl(item.getImageUrl());
			return itemDTO;
		}).toList());
		orderResponseDTO.setPayment(PaymentMapper.toDomain(order.getPayment()));
		orderResponseDTO.setPerson(person);
		orderResponseDTO.setAwaitingTime(order.getAwaitingTime());
		orderResponseDTO.setTotalPrice(order.getTotalPrice());
		return orderResponseDTO;
	}

	public static Order toDomain(OrderResponseDTO order) {
		Person person = null;
		if (order.getPerson() != null) {
			person = PersonMapper.toDomain(order.getPerson());
		}
		return new Order(order.getId(), order.getStatus(), order.getCreatedDate(), order.getUpdatedDate(),
				order.getItems().stream().map(ItemMapper::toDomain).toList(), PaymentMapper.toDTO(order.getPayment()),
				order.getAwaitingTime(), person, order.getTotalPrice());
	}

	public static Order toDomain(OrderRequestDTO order) {
		Person person = null;
		if (order.getPerson() != null) {
			person = PersonMapper.toDomain(order.getPerson());
		}
		return new Order(order.getId(),null, LocalDateTime.now(), null,
				order.getItems().stream().map(ItemMapper::toDomain).toList(),null,
				null, person, null);
	}

}
