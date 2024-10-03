package com.rb.auth.services;

import com.rb.auth.domain.product.CreateProductRequestDTO;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.product.ProductResponseDTO;
import com.rb.auth.repositories.ProductRepository;
import com.rb.auth.services.exceptions.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public Product createProduct(CreateProductRequestDTO dto) {
        var product = this.repository.findBySku(dto.sku());

        if (product.isPresent())
            throw new IllegalArgumentException("Product already exists: " + product.get().getName() + " sku: " + product.get().getSku());

        return this.repository.save(new Product(dto));
    }


    public Product updateProduct(CreateProductRequestDTO dto) {
        var product = repository.findBySku(dto.sku());

        if (product.isEmpty()) throw new Error("Product doesnt exist");

        var existingProduct = product.get();
        existingProduct.setName(dto.name());
        existingProduct.setCategory(dto.category());
        existingProduct.setSku(dto.sku());
        existingProduct.setDescription(dto.description());

        return this.repository.save(existingProduct);
    }

    public List<ProductResponseDTO> getAll() {
        return this.repository.findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();
    }

    public Product getProductById(String id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

}