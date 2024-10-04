package com.rb.auth.domain.order.dto;

import com.rb.auth.domain.product.Product;
import jakarta.validation.constraints.NotBlank;

public record CreateOrderDTO(@NotBlank Product product, int quantity) {
}
