package com.rb.auth.controllers;


import com.rb.auth.domain.sale.CreatedSaleDTO;
import com.rb.auth.domain.store.CreateStoreResponseDTO;
import com.rb.auth.domain.store.RestockProductDTO;
import com.rb.auth.domain.store.StockProductDTO;
import com.rb.auth.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
   private StoreService storeService;

    @PostMapping
    public ResponseEntity<String> createStore (@RequestBody CreateStoreResponseDTO storeDTO) {
        var storeId = storeService.createStore(storeDTO);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(storeId)
                .toUri();

        return ResponseEntity.created(location).body("Store created with ID: " + storeId);
    }
    @PostMapping("/stock/products")
    public ResponseEntity<String> stockProducts (@RequestBody @Validated StockProductDTO dto) {
        var stockId = this.storeService.stockProducts(dto.storeId(), dto.products());

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(stockId)
                .toUri();

        return ResponseEntity.created(location).body("Stocked products in stock with ID: " +  stockId);

    }

    @PostMapping("/sale")
    public ResponseEntity<String> processSale (@RequestBody CreatedSaleDTO createdSaleDTO) {

       var sale =  storeService.processSale(createdSaleDTO);

       return ResponseEntity.ok("n vendido");
    }


    @PutMapping("/sale")
    public ResponseEntity restockProduct (@RequestBody @Validated RestockProductDTO body) {

        return ResponseEntity.ok().build();
    }



}

