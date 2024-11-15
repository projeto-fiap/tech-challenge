package tech.fiap.project.domain.usecase.item;

import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.infra.entity.ItemCategory;

import java.util.List;
import java.util.Optional;

public interface RetrieveItemUseCase {

	List<Item> findAll();

	Optional<Item> findById(Long id);

	List<Item> findByCategory(ItemCategory category);

}
