package tech.fiap.project.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.fiap.project.infra.entity.KitchenEntity;
import tech.fiap.project.infra.entity.OrderEntity;

public interface KitchenRepository extends JpaRepository<KitchenEntity, Long> {

}