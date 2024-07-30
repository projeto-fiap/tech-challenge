package tech.fiap.project.domain.usecase.impl.item;

import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.item.InitializeItemUseCase;
import tech.fiap.project.domain.usecase.order.CalculateTotalOrderUseCase;
import tech.fiap.project.domain.usecase.order.CreateOrUpdateOrderUseCase;
import tech.fiap.project.domain.usecase.person.InitializePersonUseCase;
import tech.fiap.project.domain.dataprovider.OrderDataProvider;

import java.time.Duration;
import java.time.LocalDateTime;

public class CreateOrUpdateOrderUseCaseImpl implements CreateOrUpdateOrderUseCase {

	private final OrderDataProvider orderDataProvider;

	private final InitializePersonUseCase initializePersonUseCase;

	private final InitializeItemUseCase initializeItemUseCaseImpl;

	private final CalculateTotalOrderUseCase calculateTotalOrderUseCase;

	public CreateOrUpdateOrderUseCaseImpl(OrderDataProvider orderDataProvider,
										  InitializePersonUseCase initializePersonUseCase, InitializeItemUseCase initializeItemUseCaseImpl, CalculateTotalOrderUseCase calculateTotalOrderUseCase) {
		this.orderDataProvider = orderDataProvider;
		this.initializePersonUseCase = initializePersonUseCase;
		this.initializeItemUseCaseImpl = initializeItemUseCaseImpl;
		this.calculateTotalOrderUseCase = calculateTotalOrderUseCase;
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
		order.setAwaitingTime(Duration.ZERO);
		order.setCreatedDate(LocalDateTime.now());
		order.setTotalPrice(calculateTotalOrderUseCase.execute(order.getItems()));
	}

	private void updateOrder(Order order) {
		order.setUpdatedDate(LocalDateTime.now());
		order.setTotalPrice(calculateTotalOrderUseCase.execute(order.getItems()));
	}

}