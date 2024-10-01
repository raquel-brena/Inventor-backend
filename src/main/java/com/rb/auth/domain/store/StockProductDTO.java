package com.rb.auth.domain.store;

import com.rb.auth.domain.product.UpdateProductStockDTO;

import java.util.List;

public record StockProductDTO(
        String storeId,
        List<UpdateProductStockDTO> products
) {
}
