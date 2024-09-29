package com.rb.auth.controllers;

import com.rb.auth.domain.enums.Category;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.product.CreateProductRequestDTO;
import com.rb.auth.domain.product.CreateProductResponseDTO;
import com.rb.auth.repositories.ProductRepository;
import com.rb.auth.services.exceptions.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductRepository repository;

    public Product createProduct(CreateProductRequestDTO body){
        var product = this.repository.findBySku(body.sku());
        if (product.isEmpty()) {
            var newProduct = new Product(body);

            return this.repository.save(newProduct);
        }
        return null;
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Validated CreateProductRequestDTO body){
        System.out.println(body.sku());
        var product = this.repository.findBySku(body.sku());

        if (product.isPresent()) {
            Product existingProduct = product.get();
            existingProduct.setName(body.name());
            existingProduct.setCategory(body.category());
            existingProduct.setSku(body.sku());
            existingProduct.setDescription(body.description());
            this.repository.save(existingProduct);

            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateProductResponseDTO> getProduct(@PathVariable String id) {
        var product = getProductById(id);

        CreateProductResponseDTO productDto = new CreateProductResponseDTO(product);
        return ResponseEntity.ok(productDto);
    }
//    @GetMapping("/{category}")
//    public ResponseEntity getByCategory (@PathVariable Category category) {
//        List<CreateProductResponseDTO> products = this.repository.findByCategoryIn(category)
//                .stream()
//                .map(CreateProductResponseDTO::new)
//                .toList();
//
//        return ResponseEntity.ok(products);
//    }
    @GetMapping
    public ResponseEntity getAllProducts(){
        System.out.println("pegando produtos");
        List<CreateProductResponseDTO> productList = this.repository.findAll()
                .stream()
                .map(CreateProductResponseDTO::new)
                .toList();

        return ResponseEntity.ok(productList);
    }

    public Product getProductById (String id) {
       return this.repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }


}
