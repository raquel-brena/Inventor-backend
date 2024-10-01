package com.rb.auth.services;

import com.rb.auth.domain.address.Address;
import com.rb.auth.domain.address.CreateAddressDTO;
import com.rb.auth.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository repository;


    public Address createAddress(CreateAddressDTO dto) {
        var address = new Address();

        address.setCity(dto.city());
        address.setState(dto.state());
        address.setNeighborhood(dto.neighborhood());
        address.setStreetName(dto.streetName());
        address.setNumber(dto.number());

        return this.repository.save(address);
    }

    public Address updateAddress(Address address) {
        if (repository.findById(address.getId()).isEmpty()) {
            throw new Error("Address not found");
        }

        return repository.save(address);
    }
}
