package tech.fiap.project.domain.usecase.impl.kitchen;

import tech.fiap.project.domain.dataprovider.KitchenDataProvider;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.usecase.kitchen.KitchenRetrieveUseCase;

import java.util.List;
import java.util.Optional;

public class KitchenRetrieveUseCaseImpl implements KitchenRetrieveUseCase {

	private final KitchenDataProvider kitchenDataProvider;

	public KitchenRetrieveUseCaseImpl(KitchenDataProvider kitchenDataProvider) {
		this.kitchenDataProvider = kitchenDataProvider;
	}

	@Override
	public List<Kitchen> findAll() {
		return kitchenDataProvider.retrieveAll();
	}

	@Override
	public List<Kitchen> findIds(List<Long> orderIds) {
		return kitchenDataProvider.retrieveAll();
	}

	@Override
	public Optional<Kitchen> findById(Long orderId) {
		return kitchenDataProvider.retrieveById(orderId);
	}

}
