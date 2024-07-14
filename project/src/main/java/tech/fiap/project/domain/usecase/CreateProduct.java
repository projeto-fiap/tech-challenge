package tech.fiap.project.domain.usecase;

import tech.fiap.project.domain.entity.Product;

import java.util.List;

public interface CreateProduct {
    List<Product> execute(List<Product> products);
}
