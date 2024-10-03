package com.rb.auth.domain.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

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
        Integer onHand,
        Integer toBeReceived,
        Integer toBePacked
) {
    public CreateProductRequestDTO(Product product) {
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
                product.getStock().getOnHand(),
                product.getStock().getToBeReceived(),
                product.getStock().getToBePacked());
    }

    ;
}