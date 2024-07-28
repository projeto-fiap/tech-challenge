package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.Person;

import java.util.List;

public class OrderMapper {

	public static List<OrderDTO> toDTO(List<Order> orders) {
		return orders.stream().map(OrderMapper::toDTO).toList();
	}

	public static OrderDTO toDTO(Order order) {
		PersonDTO person = null;
		if (order.getPerson() != null) {
			person = PersonMapper.toDTO(order.getPerson());
		}
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getId());
		orderDTO.setStatus(order.getStatus());
		orderDTO.setCreatedDate(order.getCreatedDate());
		orderDTO.setUpdatedDate(order.getUpdatedDate());
		orderDTO.setItems(order.getItems().stream().map(ItemMapper::toDTO).toList());
		orderDTO.setPayment(PaymentMapper.toDomain(order.getPayment()));
		orderDTO.setPerson(person);
		return orderDTO;
	}

	public static Order toDomain(OrderDTO order) {
		Person person = null;
		if (order.getPerson() != null) {
			person = PersonMapper.toDomain(order.getPerson());
		}
		return new Order(order.getId(), order.getStatus(), order.getCreatedDate(), order.getUpdatedDate(),
				order.getItems().stream().map(ItemMapper::toDomain).toList(), PaymentMapper.toDTO(order.getPayment()),
                person);
	}

}
