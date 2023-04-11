package com.test.example.demo.controller;

import com.test.example.demo.model.Response;
import com.test.example.demo.model.request.ProductRequest;
import com.test.example.demo.service.ColorService;
import com.test.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(
            path = "/api/products/active",
            produces = APPLICATION_JSON_VALUE
    )
    public Response<Object> getActiveProductsById(
            @RequestBody final ProductRequest request
    ) {
        final var products = productService.getActiveProductsById(request.getProductIds());

        return Response.builder()
                .statusCode(HttpStatus.OK)
                .body(products)
                .build();
    }
    @PostMapping(
            path = "/api/products",
            produces = APPLICATION_JSON_VALUE
    )
    public Response<Object> getProductsById(
            @RequestBody final ProductRequest request
    ) {
        final var products = productService.getActiveProductsById(request.getProductIds());

        return Response.builder()
                .statusCode(HttpStatus.OK)
                .body(products)
                .build();
    }

}
