package tech.fiap.project.infra.dataprovider.mapper;

import tech.fiap.project.app.dto.OrderStatus;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.infra.entity.OrderEntity;

public class OrderRepositoryMapper {

    public static OrderEntity toEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setItems(order.getItems().stream().map(ItemRepositoryMapper::toEntity).toList());
        orderEntity.setPayment(PaymentRepositoryMapper.toEntity(order.getPayment()));
        orderEntity.setStatus(order.getStatus().name());
        orderEntity.setCreatedDate(order.getCreatedDate());
        orderEntity.setUpdatedDate(order.getUpdatedDate());
        return orderEntity;
    }

    public static Order toDomain(OrderEntity orderEntity) {
        return new Order(orderEntity.getId(),OrderStatus.valueOf(orderEntity.getStatus()),orderEntity.getCreatedDate(),orderEntity.getUpdatedDate(), orderEntity.getItems().stream().map(ItemRepositoryMapper::toDomain).toList(), PaymentRepositoryMapper.toDomain(orderEntity.getPayment()));
    }
}
