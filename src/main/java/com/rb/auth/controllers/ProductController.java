package com.rb.auth.controllers;


import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.product.ProductRequestDTO;
import com.rb.auth.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductRepository repository;

    public ResponseEntity createProduct (@RequestBody @Validated ProductRequestDTO body) {

        this.repository.save(new Product(body));

        return ResponseEntity.ok().build();
    }
}
