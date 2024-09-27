package com.rb.auth.controllers;


import com.rb.auth.domain.store.CreateStoreResponseDTO;
import com.rb.auth.domain.store.RestockProductDTO;
import com.rb.auth.domain.store.StockProductDTO;
import com.rb.auth.services.store.StoreService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/store")
public class StoreController {

    @Autowired
   private StoreService storeService;

    @PostMapping("/sale")
    public ResponseEntity<String> processSale (@RequestBody String storeId, String productId, int quantity) {

       var sale =  storeService.processSale(storeId, productId, quantity);

       //if (sale) return ResponseEntity.ok("vendido");

       return ResponseEntity.ok("n vendido");
    }

    public ResponseEntity stockProduct (@RequestBody @Validated StockProductDTO body) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/sale")
    public ResponseEntity restockProduct (@RequestBody @Validated RestockProductDTO body) {

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<String> createStore (@RequestBody @Validated CreateStoreResponseDTO store) {
        String storeCreatedId = storeService.createStore(store);
        return ResponseEntity.ok(storeCreatedId);
    }


}

