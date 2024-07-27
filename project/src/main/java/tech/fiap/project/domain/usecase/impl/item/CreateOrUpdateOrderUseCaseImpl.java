package tech.fiap.project.domain.usecase.impl.item;

import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.item.InitializeItemUseCase;
import tech.fiap.project.domain.usecase.order.CreateOrUpdateOrderUseCase;
import tech.fiap.project.domain.usecase.person.InitializePersonUseCase;
import tech.fiap.project.domain.dataprovider.OrderDataProvider;

import java.time.LocalDateTime;

public class CreateOrUpdateOrderUseCaseImpl implements CreateOrUpdateOrderUseCase {

	private final OrderDataProvider orderDataProvider;

	private final InitializePersonUseCase initializePersonUseCase;

	private final InitializeItemUseCase initializeItemUseCaseImpl;

	public CreateOrUpdateOrderUseCaseImpl(OrderDataProvider orderDataProvider,
										  InitializePersonUseCase initializePersonUseCase, InitializeItemUseCase initializeItemUseCaseImpl) {
		this.orderDataProvider = orderDataProvider;
		this.initializePersonUseCase = initializePersonUseCase;
		this.initializeItemUseCaseImpl = initializeItemUseCaseImpl;
	}

	@Override
	public Order execute(Order order) {
		initializeItemUseCaseImpl.execute(order);
		initializePersonUseCase.execute(order);
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