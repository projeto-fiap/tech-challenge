package tech.fiap.project.infra.mapper;

import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.Person;
import tech.fiap.project.infra.entity.OrderEntity;
import tech.fiap.project.infra.entity.PersonEntity;

import java.util.List;

public class OrderRepositoryMapper {

	public static List<Order> toDomain(List<OrderEntity> orders) {
		return orders.stream().map(OrderRepositoryMapper::toDomain).toList();
	}

	public static List<OrderEntity> toEntity(List<Order> orders) {
		return orders.stream().map(OrderRepositoryMapper::toEntity).toList();
	}

	public static OrderEntity toEntity(Order order) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(order.getId());
		orderEntity.setItems(order.getItems().stream().map(ItemRepositoryMapper::toEntity).toList());
		orderEntity.setPayment(PaymentRepositoryMapper.toEntity(order.getPayment()));
		orderEntity.setStatus(order.getStatus().name());
		orderEntity.setCreatedDate(order.getCreatedDate());
		orderEntity.setUpdatedDate(order.getUpdatedDate());
		orderEntity.setUser(PersonRepositoryMapper.toEntity(order.getUser()));
		return orderEntity;
	}

	public static Order toDomain(OrderEntity orderEntity) {
		Person domain = null;
		PersonEntity user = orderEntity.getUser();
		if (user != null) {
			domain = PersonRepositoryMapper.toDomain(user);
		}
		return new Order(orderEntity.getId(), OrderStatus.valueOf(orderEntity.getStatus()),
				orderEntity.getCreatedDate(), orderEntity.getUpdatedDate(),
				orderEntity.getItems().stream().map(ItemRepositoryMapper::toDomain).toList(),
				PaymentRepositoryMapper.toDomain(orderEntity.getPayment()), domain);
	}

}
