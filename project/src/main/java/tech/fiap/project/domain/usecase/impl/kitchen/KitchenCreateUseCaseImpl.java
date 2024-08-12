package tech.fiap.project.domain.usecase.impl.kitchen;

import tech.fiap.project.domain.dataprovider.KitchenDataProvider;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.usecase.kitchen.KitchenCreateUseCase;

public class KitchenCreateUseCaseImpl implements KitchenCreateUseCase {

	private final KitchenDataProvider kitchenDataProvider;

	public KitchenCreateUseCaseImpl(KitchenDataProvider kitchenDataProvider) {
		this.kitchenDataProvider = kitchenDataProvider;
	}

	@Override
	public Kitchen execute(Kitchen kitchen) {
		return kitchenDataProvider.create(kitchen);
	}

}
