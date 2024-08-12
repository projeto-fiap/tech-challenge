package tech.fiap.project.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.fiap.project.infra.entity.ItemCategory;
import tech.fiap.project.infra.entity.ItemEntity;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

	@Query("SELECT i FROM ItemEntity i WHERE i.itemCategory = :category")
	List<ItemEntity> findByCategory(ItemCategory category);

}
