package com.rb.auth.domain.sale;

import com.rb.auth.domain.product.UpdateProductStockDTO;

import java.util.List;

public record CreatedSaleDTO(String storeId,
                             List<UpdateProductStockDTO> productsDTO,
                             String authorId,
                             int discount) {
}
