package tech.fiap.project.domain.dataprovider;

import tech.fiap.project.domain.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDataProvider {

	List<Item> retrieveAll();

	Optional<Item> retrieveById(Long id);

	List<Item> saveAll(List<Item> items);

}