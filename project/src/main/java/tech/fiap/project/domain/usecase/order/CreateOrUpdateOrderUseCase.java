package tech.fiap.project.domain.usecase.order;

import tech.fiap.project.domain.entity.Order;

public interface CreateOrUpdateOrderUseCase {

	Order execute(Order order);

}
