package tech.fiap.project.domain.usecase.impl.kitchen;

import tech.fiap.project.domain.dataprovider.KitchenDataProvider;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.usecase.kitchen.KitchenCreateUseCase;
import tech.fiap.project.domain.usecase.kitchen.KitchenUpdateUseCase;

public class KitchenUpdateUseCaseImpl implements KitchenUpdateUseCase {

	private final KitchenDataProvider kitchenDataProvider;

	public KitchenUpdateUseCaseImpl(KitchenDataProvider kitchenDataProvider) {
		this.kitchenDataProvider = kitchenDataProvider;
	}

	@Override
	public Kitchen execute(Kitchen kitchen) {
		return kitchenDataProvider.create(kitchen);
	}

}
