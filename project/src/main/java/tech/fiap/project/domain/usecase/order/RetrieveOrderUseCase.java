package tech.fiap.project.domain.usecase.order;

import tech.fiap.project.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface RetrieveOrderUseCase {

	List<Order> findAll();
	Optional<Order> findById(Long id);

}
