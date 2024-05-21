package com.rb.auth.controllers;

import com.rb.auth.domain.product.Category;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.product.ProductRequestDTO;
import com.rb.auth.domain.product.ProductResponseDTO;
import com.rb.auth.repositories.ProductRepository;
import com.rb.auth.services.exceptions.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Validated ProductRequestDTO body){
        var product = this.repository.findBySku(body.sku());
        if (product.isEmpty()) {
            Product newProduct = new Product(body);

            this.repository.save(newProduct);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Validated ProductRequestDTO body){
        System.out.println(body.sku());
        var product = this.repository.findBySku(body.sku());
        if (product.isPresent()) {
            Product existingProduct = product.get();
            existingProduct.setName(body.name());
            existingProduct.setPrice(body.price());
            existingProduct.setCategory(body.category());
            existingProduct.setSku(body.sku());
            existingProduct.setDescription(body.description());
            existingProduct.setQuantity(body.quantity());
            this.repository.save(existingProduct);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable String id) {
        var product = this.repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        ProductResponseDTO productDto = new ProductResponseDTO(product);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping
    public ResponseEntity getAllProducts(){
        System.out.println("pegando produtos");
        List<ProductResponseDTO> productList = this.repository.findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();

        return ResponseEntity.ok(productList);
    }
    @GetMapping("/{category}")
    public ResponseEntity getByCategory (@PathVariable Category category) {
        List<ProductResponseDTO> products = this.repository.findByCategory(category)
                .stream()
                .map(ProductResponseDTO::new)
                .toList();

        return ResponseEntity.ok(products);
    }
}
