package tech.fiap.project.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.fiap.project.infra.entity.KitchenEntity;

public interface KitchenRepository extends JpaRepository<KitchenEntity, Long> {

}