package com.test.example.demo.service;

import com.test.example.demo.model.Product;
import com.test.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsByIds(final List<Long> productIds) {
        return productIds.stream()
                .parallel()
                .map(productRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public Map<Long, Product> getActiveProductsById(final List<Long> productIds) {
        return productIds.stream()
                .parallel()
                .map(productRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(Product::isActive)
                .collect(Collectors.toMap(Product::getId, Function.identity()));
    }

}
