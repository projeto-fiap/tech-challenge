package tech.fiap.project.domain.usecase.impl.item;

import tech.fiap.project.domain.dataprovider.ItemDataProvider;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.usecase.item.CreateItemUseCase;

import java.util.List;

public class CreateItemUseCaseImpl implements CreateItemUseCase {

    private final ItemDataProvider itemDataProvider;

    public CreateItemUseCaseImpl(ItemDataProvider itemDataProvider) {
        this.itemDataProvider = itemDataProvider;
    }

    @Override
    public List<Item> execute(List<Item> item) {
            return itemDataProvider.saveAll(item);
    }
}
