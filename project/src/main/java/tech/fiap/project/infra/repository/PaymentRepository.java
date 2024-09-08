package tech.fiap.project.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.fiap.project.infra.entity.ItemCategory;
import tech.fiap.project.infra.entity.ItemEntity;
import tech.fiap.project.infra.entity.PaymentEntity;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {


}
