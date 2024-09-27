package com.rb.auth.domain.store;

import com.rb.auth.domain.address.Address;
import com.rb.auth.domain.address.CreateAddressDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

public record CreateStoreResponseDTO (
        @NotBlank
        @Min(3)
        String name,
        Address address

) {
    public CreateStoreResponseDTO (Store store) {
        this(store.getName(), store.getAddressId());
    }
}
