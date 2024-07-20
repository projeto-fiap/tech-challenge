package tech.fiap.project.domain.usecase;

import tech.fiap.project.domain.entity.Order;

import java.util.List;

public interface RetrieveOrderUseCase {

	List<Order> findAll();

}
