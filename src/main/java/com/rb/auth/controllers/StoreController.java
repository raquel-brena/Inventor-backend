package com.rb.auth.controllers;

import com.rb.auth.domain.sale.CreatedSaleDTO;
import com.rb.auth.domain.store.CreateStoreResponseDTO;
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

    @PostMapping("/sale")
    public ResponseEntity<String> processSale(@RequestBody CreatedSaleDTO createdSaleDTO) {
        try {
            var sale = storeService.processSale(createdSaleDTO);
            return ResponseEntity.ok().body("Sale processed with ID: ");
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
