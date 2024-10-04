package com.rb.auth.domain.product.dto;

import com.rb.auth.domain.product.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductResponseDTO(
        @NotBlank
        String name,
        String description,
        @NotBlank
        int variants,
        @NotNull
        String gender,
        float retail_price,
        float wholesale_price,
        @NotNull
        String sku,
        @NotNull
        String status,
        String barcode,
        String unit,
        @NotNull
        String category,

        @NotBlank
        Integer quantity
) {

    public ProductResponseDTO(Product product) {
        this(product.getName(),
                product.getDescription(),
                product.getVariants(),
                product.getGender(),
                product.getRetail_price(),
                product.getWholesale_price(),
                product.getSku(),
                product.getStatus(),
                product.getBarcode(),
                product.getUnit(),
                product.getCategory(),
                product.getStock().getOnHand());
    }

    ;
}
