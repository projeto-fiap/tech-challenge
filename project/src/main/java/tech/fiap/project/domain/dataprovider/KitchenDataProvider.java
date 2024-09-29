package tech.fiap.project.domain.dataprovider;

import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface KitchenDataProvider {

	List<Kitchen> retrieveAll();

	Kitchen create(Kitchen kitchen);

	Kitchen update(Kitchen kitchen);

	Optional<Kitchen> retrieveById(Long orderId);

}
