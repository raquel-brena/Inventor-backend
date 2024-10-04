package com.rb.auth.controllers;

import com.rb.auth.domain.product.dto.CreateProductRequestDTO;
import com.rb.auth.domain.product.dto.ProductResponseDTO;
import com.rb.auth.domain.product.dto.UpdateProductStockDTO;
import com.rb.auth.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequestDTO dto) {
        try {
            var product = this.service.createProduct(dto);

            var location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(product.getId())
                    .toUri();
            return ResponseEntity.created(location).body("Product created with ID: " + product.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody @Validated CreateProductRequestDTO dto) {
        var updatedProduct = this.service.updateProduct(dto);
        return ResponseEntity.ok().body("Product updated with ID: " + updatedProduct.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable String id) {
        var product = this.service.getProductById(id);

        var productDto = new ProductResponseDTO(product);
        return ResponseEntity.ok().body(productDto);
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        var productResponseDTO = this.service.getAll();

        return ResponseEntity.ok().body("Products: " + productResponseDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity updateStock (@PathVariable String id, @RequestBody UpdateProductStockDTO dto) {
        return ResponseEntity.ok().body("");
    };

}
