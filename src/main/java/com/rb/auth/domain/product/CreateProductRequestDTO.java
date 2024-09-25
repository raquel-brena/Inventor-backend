package com.rb.auth.domain.product;

import org.springframework.format.annotation.NumberFormat;

import com.rb.auth.domain.enums.Category;
import com.rb.auth.domain.enums.Gender;
import com.rb.auth.domain.stock.Stock;
import com.rb.auth.domain.enums.Status;
import com.rb.auth.domain.enums.Unit;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record CreateProductRequestDTO(
        @NotBlank
        @Min(3)
        String name,
        @Size(min = 3, max = 255)
        String description,
        @NotBlank
        @NumberFormat(style = NumberFormat.Style.NUMBER)
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
        Category category,
        @NotBlank
        Integer quantity
) {
}