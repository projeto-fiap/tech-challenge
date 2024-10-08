package tech.fiap.project.domain.usecase.impl.order;

import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.dataprovider.OrderDataProvider;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;

import java.util.List;
import java.util.Optional;

public class RetrieveOrderUseCaseImpl implements RetrieveOrderUseCase {

	private final OrderDataProvider orderDataProvider;

	public RetrieveOrderUseCaseImpl(OrderDataProvider orderDataProvider) {
		this.orderDataProvider = orderDataProvider;
	}

	@Override
	public List<Order> findAll() {
		return orderDataProvider.retrieveAll();
	}

	@Override
	public Optional<Order> findById(Long id) {
		return orderDataProvider.retrieveById(id);
	}

	@Override
	public Optional<Order> findByIdWithPayment(Long id) {
		return orderDataProvider.retrieveByIdWithPayment(id);
	}
}
