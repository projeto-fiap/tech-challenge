package tech.fiap.project.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.fiap.project.infra.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByCategory(String category);
    List<ProductEntity> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
