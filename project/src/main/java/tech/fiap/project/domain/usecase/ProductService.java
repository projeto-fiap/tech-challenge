package tech.fiap.project.domain.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.fiap.project.app.adapter.ProductMapper;
import tech.fiap.project.domain.entity.Product;
import tech.fiap.project.infra.entity.ProductEntity;
import tech.fiap.project.infra.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public List<Product> createProducts(List<Product> products) {
        List<ProductEntity> productEntities = productMapper.toEntityList(products);
        List<ProductEntity> savedEntities = productRepository.saveAll(productEntities);
        return productMapper.toDomainList(savedEntities);
    }

    public List<Product> getAllProducts() {
        return productMapper.toDomainList(productRepository.findAll());
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id).map(productMapper::toDomain);
    }

    public List<Product> getProductsByCategory(String category) {
        return productMapper.toDomainList(productRepository.findByCategory(category));
    }

    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productMapper.toDomainList(productRepository.findByPriceBetween(minPrice, maxPrice));
    }
}
