package tech.fiap.project.app.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.fiap.project.app.adapter.ProductMapper;
import tech.fiap.project.app.dto.ProductDTO;
import tech.fiap.project.domain.entity.Product;
import tech.fiap.project.domain.usecase.ProductService;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<List<ProductDTO>> createProducts(@Valid @RequestBody List<ProductDTO> productDTOs) {
        List<Product> products = productDTOs.stream()
                .map(productMapper::toDomain)
                .collect(Collectors.toList());
        List<Product> savedProducts = productService.createProducts(products);
        List<ProductDTO> result = productMapper.toDTOList(savedProducts);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOs = productMapper.toDTOList(products);
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(productMapper.toDTO(product)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        List<ProductDTO> productDTOs = productMapper.toDTOList(products);
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/price")
    public ResponseEntity<List<ProductDTO>> getProductsByPriceRange(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        List<ProductDTO> productDTOs = productMapper.toDTOList(products);
        return ResponseEntity.ok(productDTOs);
    }
}
