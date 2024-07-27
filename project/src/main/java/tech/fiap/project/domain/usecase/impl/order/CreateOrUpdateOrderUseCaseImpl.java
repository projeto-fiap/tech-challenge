package tech.fiap.project.domain.usecase.impl.order;

import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.order.CreateOrUpdateOrderUseCase;
import tech.fiap.project.domain.usecase.user.InitializeUserUseCase;
import tech.fiap.project.domain.dataprovider.OrderDataProvider;

import java.time.LocalDateTime;

public class CreateOrUpdateOrderUseCaseImpl implements CreateOrUpdateOrderUseCase {

	private final OrderDataProvider orderDataProvider;

	private final InitializeUserUseCase initializeUserUseCase;

	public CreateOrUpdateOrderUseCaseImpl(OrderDataProvider orderDataProvider,
			InitializeUserUseCase initializeUserUseCase) {
		this.orderDataProvider = orderDataProvider;
		this.initializeUserUseCase = initializeUserUseCase;
	}

	@Override
	public Order execute(Order order) {
		initializeUserUseCase.execute(order);
		if (orderDataProvider.retrieveAll(order).isEmpty()) {
			this.initializeOrder(order);
		}
		else {
			this.updateOrder(order);
		}
		return orderDataProvider.create(order);
	}

	private void initializeOrder(Order order) {
		order.setStatus(OrderStatus.PENDING);
		order.setCreatedDate(LocalDateTime.now());
	}

	private void updateOrder(Order order) {
		order.setUpdatedDate(LocalDateTime.now());
	}

}