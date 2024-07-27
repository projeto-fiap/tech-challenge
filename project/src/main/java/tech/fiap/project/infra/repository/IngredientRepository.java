package tech.fiap.project.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.fiap.project.infra.entity.IngredientItemEntity;

public interface IngredientRepository extends JpaRepository<IngredientItemEntity, Long> {

}
