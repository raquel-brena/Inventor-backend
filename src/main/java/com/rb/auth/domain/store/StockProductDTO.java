package com.rb.auth.domain.store;

import com.rb.auth.domain.product.CreateProductRequestDTO;

import java.util.List;

public record StockProductDTO(
        String storeId,
        List<CreateProductRequestDTO> products
) {
}
