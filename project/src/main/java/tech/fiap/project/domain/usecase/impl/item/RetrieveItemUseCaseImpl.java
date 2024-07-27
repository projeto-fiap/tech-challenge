package tech.fiap.project.domain.usecase.impl.item;

import tech.fiap.project.domain.dataprovider.ItemDataProvider;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.usecase.item.RetrieveItemUseCase;

import java.util.List;
import java.util.Optional;

public class RetrieveItemUseCaseImpl implements RetrieveItemUseCase {

	private final ItemDataProvider itemDataProvider;

	public RetrieveItemUseCaseImpl(ItemDataProvider itemDataProvider) {
		this.itemDataProvider = itemDataProvider;
	}

	@Override
	public List<Item> findAll() {
		return itemDataProvider.retrieveAll();
	}

	@Override
	public Optional<Item> findById(Long id) {
		return itemDataProvider.retrieveById(id);
	}

}
