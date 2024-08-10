package tech.fiap.project.domain.dataprovider;

import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.infra.entity.ItemCategory;

import java.util.List;
import java.util.Optional;

public interface ItemDataProvider {

	List<Item> retrieveAll();

	Optional<Item> retrieveById(Long id);

	List<Item> saveAll(List<Item> items);

	Item save(Item item);

	void deleteById(Long id);
	List<Item> retrieveByCategory(ItemCategory category);

}
