package com.rb.auth.domain.store;

import com.rb.auth.domain.address.CreateAddressDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateStoreResponseDTO (
        @NotBlank
        @Min(3)
        String name,
        CreateAddressDTO address
) {
}
