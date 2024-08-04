package tech.fiap.project.domain.usecase.impl.item;

import tech.fiap.project.domain.dataprovider.ItemDataProvider;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.item.InitializeItemUseCase;
import tech.fiap.project.infra.exception.ItemNotFound;
import tech.fiap.project.infra.exception.NullIdException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InitializeItemUseCaseImpl implements InitializeItemUseCase {

	private final ItemDataProvider itemDataProvider;

	public InitializeItemUseCaseImpl(ItemDataProvider itemDataProvider) {
		this.itemDataProvider = itemDataProvider;
	}

	@Override
	public void execute(Order order) {
		List<Item> items = order.getItems();
		List<Item> itemsSaved = new ArrayList<>();
		items.forEach(item -> {
			Long id = item.getId();
			if (id == null) {
				throw new NullIdException();
			}
			Optional<Item> itemSaved = itemDataProvider.retrieveById(id);
			if (itemSaved.isPresent()) {
				itemsSaved.add(itemSaved.get());
			}
			else {
				throw new ItemNotFound(id);
			}
		});
		order.setItems(itemsSaved);
	}

}
