package com.rb.auth.domain.product;

import com.rb.auth.domain.enums.Category;
import com.rb.auth.domain.enums.Gender;
import com.rb.auth.domain.enums.Status;
import com.rb.auth.domain.enums.Unit;
import com.rb.auth.domain.stock.Stock;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductResponseDTO(
      @NotBlank
        String name,
        String description,
        @NotBlank
        int variants,
        @NotNull
        Stock stock,
        @NotNull
        Gender gender,
        float retail_price,
        float wholesale_price,
        @NotNull
        String sku,
        @NotNull
        Status status,
        String barcode,
        Unit unit,
        @NotNull
        Category category) {

    public CreateProductResponseDTO(Product product){
        this(product.getName(), 
        product.getDescription(), 
        product.getVariants(),
        product.getStock(),
        product.getGender(),
        product.getRetail_price(),
        product.getWholesale_price(),
        product.getSku(), 
        product.getStatus(),
        product.getBarcode(),
        product.getUnit(),
        product.getCategory());
    };
    }
