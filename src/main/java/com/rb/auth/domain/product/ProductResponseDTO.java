package com.rb.auth.domain.product;

import java.util.List;

public record ProductResponseDTO(String name, Integer price, String sku, String description, Category category, Integer quantity) {
    public ProductResponseDTO(Product product){
        this(product.getName(), product.getPrice(),product.getSku(), product.getDescription(), product.getCategory(), product.getQuantity());
    }


}