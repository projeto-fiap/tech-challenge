package tech.fiap.project.domain.dataprovider;

import tech.fiap.project.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDataProvider {

	Optional<Order> retrieveAll(Order order);

	List<Order> retrieveAll();

	Order create(Order order);

	Optional<Order> retrieveById(Long id);

}
