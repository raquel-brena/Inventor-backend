package com.rb.auth.services;

import com.rb.auth.domain.product.CreateProductRequestDTO;
import com.rb.auth.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;
    public boolean atualizarProduto (CreateProductRequestDTO productDTO, String productId) {
        var product = repository.findById(productId);

        if (product.isEmpty()) throw new Error("Produto não existe");


        return false;
    }
}
