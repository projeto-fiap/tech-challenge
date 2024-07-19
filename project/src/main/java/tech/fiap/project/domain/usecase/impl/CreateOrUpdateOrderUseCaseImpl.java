package tech.fiap.project.domain.usecase.impl;

import tech.fiap.project.app.dto.OrderStatus;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.OrderDataProvider;
import tech.fiap.project.domain.usecase.CreateOrUpdateOrderUseCase;

import java.time.LocalDateTime;

public class CreateOrUpdateOrderUseCaseImpl implements CreateOrUpdateOrderUseCase {

    private final OrderDataProvider orderDataProvider;

    public CreateOrUpdateOrderUseCaseImpl(OrderDataProvider orderDataProvider) {
        this.orderDataProvider = orderDataProvider;
    }

    @Override
    public Order execute(Order order) {
        if (!orderDataProvider.retrieve(order).isPresent()) {
            this.initializeOrder(order);
        }else {
            this.updateOrder(order);
        }
        return orderDataProvider.create(order);

    }
    private void initializeOrder(Order order){
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedDate(LocalDateTime.now());
    }
    private void updateOrder(Order order){
        order.setUpdatedDate(LocalDateTime.now());
    }
}

