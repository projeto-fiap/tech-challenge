package tech.fiap.project.domain.usecase.item;

import tech.fiap.project.domain.entity.Item;

public interface UpdateItemUseCase {

	Item execute(Long id, Item item);

}
