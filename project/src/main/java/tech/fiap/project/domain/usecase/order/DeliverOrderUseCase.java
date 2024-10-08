package tech.fiap.project.domain.usecase.order;

import tech.fiap.project.domain.entity.Order;

public interface DeliverOrderUseCase {

	Order execute(Long id);

}
