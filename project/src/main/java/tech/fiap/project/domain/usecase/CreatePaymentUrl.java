package tech.fiap.project.domain.usecase;

import tech.fiap.project.domain.entity.Order;

public interface CreatePaymentUrl {
    String execute(Order order);
}
