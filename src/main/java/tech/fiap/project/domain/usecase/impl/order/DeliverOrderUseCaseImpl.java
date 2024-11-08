package tech.fiap.project.domain.usecase.impl.order;

import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.domain.usecase.order.CreateOrUpdateOrderUseCase;
import tech.fiap.project.domain.usecase.order.DeliverOrderUseCase;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;
import tech.fiap.project.infra.exception.OrderNotFound;

import java.util.Optional;

public class DeliverOrderUseCaseImpl implements DeliverOrderUseCase {

	private final CreateOrUpdateOrderUseCase createOrUpdateOrderUsecase;

	private final RetrieveOrderUseCase retrieveOrderUseCase;

	public DeliverOrderUseCaseImpl(CreateOrUpdateOrderUseCase createOrUpdateOrderUsecase,
			RetrieveOrderUseCase retrieveOrderUseCase) {
		this.createOrUpdateOrderUsecase = createOrUpdateOrderUsecase;
		this.retrieveOrderUseCase = retrieveOrderUseCase;
	}

	@Override
	public Order execute(Long id) {
		return deliverOrder(id);
	}

	private Order deliverOrder(Long id) {
		Optional<Order> orderSaved = retrieveOrderUseCase.findByIdWithPayment(id);
		if (orderSaved.isPresent()) {
			if (orderSaved.get().getStatus() == OrderStatus.FINISHED) {
				throw new OrderNotFound(id);
			}
			Order order = orderSaved.get();
			order.setStatus(OrderStatus.FINISHED);
			return createOrUpdateOrderUsecase.execute(order);
		}
		else {
			throw new OrderNotFound(id);
		}
	}

}
