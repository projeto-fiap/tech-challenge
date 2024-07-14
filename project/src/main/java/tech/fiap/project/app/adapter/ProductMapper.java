package tech.fiap.project.app.adapter;

import org.springframework.stereotype.Component;
import tech.fiap.project.app.dto.ProductDTO;
import tech.fiap.project.domain.entity.Product;
import tech.fiap.project.infra.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductEntity(
        );
    }

    public Product toDomain(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.getCategory(),
                productEntity.getImage()
        );
    }

    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getImage()
        );
    }

    public Product toDomain(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getCategory(),
                productDTO.getImage()
        );
    }

    public List<ProductEntity> toEntityList(List<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<Product> toDomainList(List<ProductEntity> productEntities) {
        if (productEntities == null) {
            return null;
        }
        return productEntities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> toDTOList(List<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
