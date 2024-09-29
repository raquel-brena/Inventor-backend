package com.rb.auth.controllers;

import com.rb.auth.domain.product.CreateProductRequestDTO;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.product.SaleProductDTO;
import com.rb.auth.domain.stock.Stock;
import com.rb.auth.domain.stock.StockRequestDTO;
import com.rb.auth.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    StockRepository repository;

    @Autowired
    ProductController productController;

    public Stock createStock () {
        return repository.save(new Stock());
    }


    @GetMapping("/{id}")
    public ResponseEntity getStock (@PathVariable String id) {

        return ResponseEntity.ok().body(this.repository.findById(Long.valueOf(id)));
    }


     public Product getProductIfExists (Stock stock, String productId){
        var exists = stock.getProducts()
                .stream()
                .filter(product -> product.getId().equals(productId)).findFirst();

         return exists.orElse(null);
     }

    public Stock updateStock(Stock stock) {
        if (repository.findById(stock.getId()).isEmpty()) {
            throw new Error("Stock not found");
        }

        return repository.save(stock);
    }

    public Long stockProducts(Stock stock, List<CreateProductRequestDTO> products) {
        List <Product> productsToStock = new ArrayList<>();

        for (CreateProductRequestDTO dto : products) {
            var product = this.productController.createProduct(dto);
            productsToStock.add(product);
        }

        stock.setProducts(productsToStock);


        return this.repository.save(stock).getId();
    }

     public boolean checkIfHasStock (int quantityProduct, int quantitySale) {
        return quantityProduct < quantitySale;
    }
}
