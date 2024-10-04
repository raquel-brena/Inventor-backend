package com.rb.auth.controllers;

import com.rb.auth.domain.product.dto.CreateProductRequestDTO;
import com.rb.auth.domain.store.dto.CreateStoreResponseDTO;
import com.rb.auth.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity<String> createStore(@RequestBody CreateStoreResponseDTO storeDTO) {
        try {
            var storeId = storeService.createStore(storeDTO);

            var location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(storeId)
                    .toUri();

            return ResponseEntity.created(location).body("Store created with ID: " + storeId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/{id}/product")
    public ResponseEntity<String> addProductToStore(@PathVariable String id, @RequestBody CreateProductRequestDTO productDTO) {
        try {
            var productId = storeService.addProductToStock(id, productDTO);
            return ResponseEntity.ok().body("Product added with ID: " + productId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getStore(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(storeService.getStore(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity getStock(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(storeService.getStock(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
