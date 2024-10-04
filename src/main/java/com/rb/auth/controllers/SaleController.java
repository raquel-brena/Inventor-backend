package com.rb.auth.controllers;


import com.rb.auth.domain.sale.dto.CreatedSaleDTO;
import com.rb.auth.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sale")
@Controller
public class SaleController {

    @Autowired
    SaleService saleService;

    @PostMapping
    public ResponseEntity<String> create (@RequestBody CreatedSaleDTO createdSaleDTO) {
        try {
            var sale = saleService.create(createdSaleDTO);
            return ResponseEntity.ok().body("Sale processed with ID: " + sale.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
