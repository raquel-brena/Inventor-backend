package com.rb.auth.services;

import com.rb.auth.domain.stock.Stock;
import com.rb.auth.domain.stock.dto.UpdateStockDTO;
import com.rb.auth.repositories.StockRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    public Stock createStock() {
        return repository.save(new Stock());
    }

    public Stock getStockById(Long id) {
        Optional<Stock> stock = repository.findById(id);
        return stock.orElseThrow(() -> new Error("Stock not found for ID: " + id));
    }

    public Stock updateStock(Long id, UpdateStockDTO dto) {
        var existingStock = getStockById(id);

        BeanUtils.copyProperties(dto, existingStock, "id", "createdAt");

        return repository.save(existingStock);
    }

    public boolean checkIfHasStock(int quantityProduct, int quantitySale) {
        return quantityProduct >= quantitySale;
    }

}
