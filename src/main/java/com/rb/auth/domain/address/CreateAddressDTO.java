package com.rb.auth.domain.address;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

public record CreateAddressDTO(
        @NotBlank
        String state,
        @NotBlank
        String city,
        @NotBlank
        String streetName,
        @NotBlank
        String neighborhood,
        @NotBlank
        @NumberFormat(style = NumberFormat.Style.NUMBER)
        Integer number
) {
}
