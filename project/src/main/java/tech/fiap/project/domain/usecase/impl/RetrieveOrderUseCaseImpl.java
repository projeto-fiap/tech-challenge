package tech.fiap.project.domain.usecase.impl;

import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.OrderDataProvider;
import tech.fiap.project.domain.usecase.RetrieveOrderUseCase;

import java.util.List;

public class RetrieveOrderUseCaseImpl implements RetrieveOrderUseCase {

    private final OrderDataProvider orderDataProvider;

    public RetrieveOrderUseCaseImpl(OrderDataProvider orderDataProvider) {
        this.orderDataProvider = orderDataProvider;
    }

    @Override
    public List<Order> findAll() {
        return orderDataProvider.retrieve();
    }
}
