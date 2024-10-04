package com.rb.auth.domain.sale.dto;

import com.rb.auth.domain.product.dto.UpdateProductStockDTO;

import java.util.List;

public record CreatedSaleDTO(String storeId,
                             List<UpdateProductStockDTO> productsDTO,
                             String authorId,
                             int discount
                             ) {
}
