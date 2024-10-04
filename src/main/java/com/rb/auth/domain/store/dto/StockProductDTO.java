package com.rb.auth.domain.store.dto;

import com.rb.auth.domain.product.dto.UpdateProductStockDTO;

import java.util.List;

public record StockProductDTO(
        String storeId,
        List<UpdateProductStockDTO> products
) {
}
