package tech.fiap.project.domain.usecase;

import tech.fiap.project.domain.entity.Order;

import java.util.Optional;

public interface OrderDataProvider {
    Optional<Order> retrieve(Order order);

    Order create(Order order);
}
