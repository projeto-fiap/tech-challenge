package tech.fiap.project.domain.usecase.item;


import tech.fiap.project.domain.entity.Item;

import java.util.List;

public interface CreateItemUseCase {
    List<Item> execute(List<Item> items);
}

