package tech.fiap.project.infra.dataprovider;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.dataprovider.OrderDataProvider;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.infra.entity.OrderEntity;
import tech.fiap.project.infra.mapper.OrderRepositoryMapper;
import tech.fiap.project.infra.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderDataProviderImpl implements OrderDataProvider {

	private OrderRepository orderRepository;

	@Override
	public Optional<Order> retrieveAll(Order order) {
		if (order.getId() == null) {
			return Optional.empty();
		}
		else {
			Optional<OrderEntity> orderEntity = orderRepository.findById(order.getId());
			return orderEntity.map(OrderRepositoryMapper::toDomain);
		}
	}

	@Override
	public List<Order> retrieveAll() {
		return OrderRepositoryMapper.toDomain(orderRepository.findAll());
	}

	@Override
	public List<Order> retrieveAllById(List<Long> id) {
		return OrderRepositoryMapper.toDomain(orderRepository.findAllById(id));
	}

	@Override
	public Order create(Order order) {

		OrderEntity entity = OrderRepositoryMapper.toEntity(order);

		OrderEntity orderSaved = orderRepository.save(entity);
		return OrderRepositoryMapper.toDomain(orderSaved);
	}

	@Override
	public Optional<Order> retrieveById(Long id) {
		return orderRepository.findById(id).map(OrderRepositoryMapper::toDomain);
	}

	@Override
	public Optional<Order> retrieveByIdWithPayment(Long id) {
		return orderRepository.findById(id).map(OrderRepositoryMapper::toDomain);
	}

}
