package com.rb.auth.controllers;

import com.rb.auth.domain.stock.Stock;
import com.rb.auth.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<String> createStock() {
        var stock = stockService.createStock();

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(stock.getId())
                .toUri();

        return ResponseEntity.created(location).body("Stock created with ID: " + stock.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStock(@PathVariable String id) {
        Stock stock = stockService.getStockById(Long.valueOf(id));
        return ResponseEntity.ok(stock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable String id, @RequestBody Stock stock) {
        Stock updatedStock = stockService.updateStock(stock);
        return ResponseEntity.ok(updatedStock);
    }


    @GetMapping("/hasStock")
    public ResponseEntity<Boolean> checkIfHasStock(@RequestParam int quantityProduct, @RequestParam int quantitySale) {
        boolean hasStock = stockService.checkIfHasStock(quantityProduct, quantitySale);
        return ResponseEntity.ok(hasStock);
    }
}
