package tech.fiap.project.domain.usecase;

import tech.fiap.project.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDataProvider {
    Optional<Order> retrieve(Order order);
    List<Order> retrieve();
    Order create(Order order);
}
