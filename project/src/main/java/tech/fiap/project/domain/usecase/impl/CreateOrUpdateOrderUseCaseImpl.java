package tech.fiap.project.domain.usecase.impl;

import tech.fiap.project.app.dto.OrderStatus;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.usecase.OrderDataProvider;
import tech.fiap.project.domain.usecase.CreateOrUpdateOrderUseCase;
import tech.fiap.project.domain.usecase.UserDataProvider;

import java.time.LocalDateTime;
import java.util.Optional;

public class CreateOrUpdateOrderUseCaseImpl implements CreateOrUpdateOrderUseCase {

    private final OrderDataProvider orderDataProvider;

    private final UserDataProvider userDataProvider;

    public CreateOrUpdateOrderUseCaseImpl(OrderDataProvider orderDataProvider, UserDataProvider userDataProvider) {
        this.orderDataProvider = orderDataProvider;
        this.userDataProvider = userDataProvider;
    }

    @Override
    public Order execute(Order order) {
        initializeUser(order);
        if (orderDataProvider.retrieve(order).isEmpty()) {
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

    private void initializeUser(Order order){
        User user = order.getUser();
        Optional<User> userSaved = userDataProvider.retrieveByEmail(user.getEmail());
        userSaved.ifPresent(order::setUser);
    }
}

