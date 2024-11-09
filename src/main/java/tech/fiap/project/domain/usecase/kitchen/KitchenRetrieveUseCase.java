package tech.fiap.project.domain.usecase.kitchen;

import tech.fiap.project.domain.entity.Kitchen;

import java.util.List;
import java.util.Optional;

public interface KitchenRetrieveUseCase {

	List<Kitchen> findAll();

	Optional<Kitchen> findById(Long orderId);

	List<Kitchen> findIds(List<Long> orderIds);

}
