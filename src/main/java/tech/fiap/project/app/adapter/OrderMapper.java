package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.*;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.domain.entity.Person;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OrderMapper {

	private OrderMapper() {

	}

	public static List<OrderResponseDTO> toDTO(List<Order> orders) {
		return orders.stream().map(OrderMapper::toDTO).toList();
	}

	public static List<OrderResponseDTO> toDTO(List<Order> orders, List<Kitchen> kitchens) {
		return orders.stream().map(order -> {
			Optional<Kitchen> kitchen = kitchens.stream()
					.filter(kitchen1 -> Objects.equals(kitchen1.getOrderId(), order.getId())).findFirst();
			return toDTO(order, kitchen);
		}).toList();
	}

	public static OrderResponseDTO toDTO(Order order, Optional<Kitchen> kitchen) {
		PersonDTO person = null;
		if (order.getPerson() != null) {
			person = PersonMapper.toDTO(order.getPerson());
		}
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		orderResponseDTO.setId(order.getId());
		orderResponseDTO.setStatus(order.getStatus());
		orderResponseDTO.setCreatedDate(order.getCreatedDate());
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
		orderResponseDTO.setPayment(order.getPayments().stream().map(PaymentMapper::toDomain).toList());
		orderResponseDTO.setPerson(person);
		orderResponseDTO.setAwaitingTime(order.getAwaitingTime());
		orderResponseDTO.setTotalPrice(order.getTotalPrice());
		kitchen.ifPresent(value -> orderResponseDTO.setKitchenQueue(KitchenMapper.toDTO(value)));
		return orderResponseDTO;
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
		if (order.getPayments() != null) {
			orderResponseDTO.setPayment(order.getPayments().stream().map(PaymentMapper::toDomain).toList());
		}
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
		List<PaymentDTO> list = null;
		List<Payment> payments = order.getPayments();
		if (payments != null) {
			list = payments.stream().map(PaymentMapper::toDomain).toList();
		}
		orderResponseDTO.setPayment(list);
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
		List<ItemDTO> items = order.getItems();
		if (items == null) {
			items = List.of();
		}
		List<PaymentDTO> payment = order.getPayment();
		if (payment == null) {
			payment = List.of();
		}
		return new Order(order.getId(), order.getStatus(), order.getCreatedDate(), order.getUpdatedDate(),
				items.stream().map(ItemMapper::toDomain).toList(), payment.stream().map(PaymentMapper::toDTO).toList(),
				order.getAwaitingTime(), person, order.getTotalPrice());
	}

	public static Order toDomain(OrderRequestDTO order) {
		Person person = null;
		if (order.getPerson() != null) {
			person = PersonMapper.toDomain(order.getPerson());
		}
		List<ItemRequestDTO> items = order.getItems();
		if (items == null) {
			items = List.of();
		}
		return new Order(order.getId(), null, LocalDateTime.now(), null,
				items.stream().map(ItemMapper::toDomain).toList(), null, null, person, null);
	}

}
