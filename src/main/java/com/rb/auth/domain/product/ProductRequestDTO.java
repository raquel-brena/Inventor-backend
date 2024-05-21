package com.rb.auth.domain.product;


public record ProductRequestDTO(
        String name,
        String description,
        String sku,
        Integer price,
        Category category,
        Integer quantity
) {
}