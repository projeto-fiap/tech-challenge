package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.domain.entity.Order;

public class OrderMapper {
    public static OrderDTO toDTO(Order order){
        return new OrderDTO(order.getId(),order.getStatus(), order.getCreatedDate(), order.getUpdatedDate(), order.getItems().stream().map(ItemMapper::toDTO).toList(), PaymentMapper.toDomain(order.getPayment()));
    }

    public static Order toDomain(OrderDTO order){
        return new Order(order.getId(),order.getStatus(), order.getCreatedDate(), order.getUpdatedDate(), order.getItems().stream().map(ItemMapper::toDomain).toList(), PaymentMapper.toDTO(order.getPaymentDTO()));
    }
}
