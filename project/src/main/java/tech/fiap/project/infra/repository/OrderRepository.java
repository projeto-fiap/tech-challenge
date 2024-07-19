package tech.fiap.project.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.fiap.project.infra.entity.OrderEntity;
import tech.fiap.project.infra.entity.UserEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}