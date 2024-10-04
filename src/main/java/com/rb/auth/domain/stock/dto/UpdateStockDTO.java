package com.rb.auth.domain.stock.dto;

public record UpdateStockDTO(
        int onHand,
        int toBeReceived,
        int toBePacked
) {
}
