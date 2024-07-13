package tech.fiap.project.app.adapter;

import org.springframework.stereotype.Service;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.domain.entity.Order;

@Service
public class OrderMapper {
    public OrderDTO toDTO(Order order) {
        return new OrderDTO(order.getId(), order.getDescription(), order.getExternalReference(), order.getTitle(), order.getNotificationUrl(), order.getQrData(), order.getAmount());
    }

    public Order toEntity(OrderDTO orderDTO) {
        return new Order(orderDTO.getId(), orderDTO.getDescription(), orderDTO.getExternalReference(), orderDTO.getTitle(), orderDTO.getNotificationUrl(), orderDTO.getQrData(), orderDTO.getAmount());
    }
}
