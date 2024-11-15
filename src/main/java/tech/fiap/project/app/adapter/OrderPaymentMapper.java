package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.*;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.Person;

public class OrderPaymentMapper {

	private OrderPaymentMapper() {

	}

	public static OrderResponsePaymentDTO toDTO(Order order) {
		PersonDTO person = null;
		if (order.getPerson() != null) {
			person = PersonMapper.toDTO(order.getPerson());
		}
		return new OrderResponsePaymentDTO(order.getId(), order.getStatus(), order.getCreatedDate(),
				order.getUpdatedDate(), order.getItems().stream().map(ItemMapper::toDTO).toList(), person,
				order.getAwaitingTime(), order.getTotalPrice());

	}

	public static Order toDomain(OrderResponsePaymentDTO order) {
		Person person = null;
		if (order.getPerson() != null) {
			person = PersonMapper.toDomain(order.getPerson());
		}
		return new Order(order.getId(), order.getStatus(), order.getCreatedDate(), order.getUpdatedDate(),
				order.getItems().stream().map(ItemMapper::toDomain).toList(), null, order.getAwaitingTime(), person,
				order.getTotalPrice());
	}

}
