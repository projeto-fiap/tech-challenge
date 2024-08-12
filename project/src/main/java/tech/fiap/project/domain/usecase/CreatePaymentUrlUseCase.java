package tech.fiap.project.domain.usecase;

import tech.fiap.project.domain.entity.Order;

public interface CreatePaymentUrlUseCase {

	String execute(Order order);

}
