package com.rb.auth.domain.stock;

import com.rb.auth.domain.product.UpdateProductStockDTO;

import java.util.List;

public record UpdateStockedProductsDTO (Long stockId, List<UpdateProductStockDTO> products) {
}
