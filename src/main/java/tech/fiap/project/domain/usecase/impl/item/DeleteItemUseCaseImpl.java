package tech.fiap.project.domain.usecase.impl.item;

import tech.fiap.project.domain.dataprovider.ItemDataProvider;
import tech.fiap.project.domain.usecase.item.DeleteItemUseCase;

public class DeleteItemUseCaseImpl implements DeleteItemUseCase {

	private final ItemDataProvider itemDataProvider;

	public DeleteItemUseCaseImpl(ItemDataProvider itemDataProvider) {
		this.itemDataProvider = itemDataProvider;
	}

	@Override
	public void execute(Long id) {
		itemDataProvider.deleteById(id);
	}

}
