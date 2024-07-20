package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.app.dto.UserDTO;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.infra.entity.UserEntity;

import java.util.List;

public class OrderMapper {

    public static List<OrderDTO> toDTO(List<Order> orders){
        return orders.stream().map(OrderMapper::toDTO).toList();
    }
    public static OrderDTO toDTO(Order order){
        UserDTO user = null;
        if (order.getUser() != null) {
            user = UserMapper.toDTO(order.getUser());
        }
        return new OrderDTO(order.getId(),order.getStatus(), order.getCreatedDate(), order.getUpdatedDate(), order.getItems().stream().map(ItemMapper::toDTO).toList(), PaymentMapper.toDomain(order.getPayment()), user);
    }

    public static Order toDomain(OrderDTO order){
        User user = null;
        if (order.getUserDTO() != null) {
            user = UserMapper.toDomain(order.getUserDTO());
        }
        return new Order(order.getId(),order.getStatus(), order.getCreatedDate(), order.getUpdatedDate(), order.getItems().stream().map(ItemMapper::toDomain).toList(), PaymentMapper.toDTO(order.getPaymentDTO()),user);
    }
}
