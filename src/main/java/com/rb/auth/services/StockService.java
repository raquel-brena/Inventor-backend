package com.rb.auth.services;

import com.rb.auth.domain.product.CreateProductRequestDTO;
import com.rb.auth.domain.product.ProductResponseDTO;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.product.UpdateProductStockDTO;
import com.rb.auth.domain.stock.Stock;
import com.rb.auth.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;


    @Autowired
    private ProductService productService;

    public Stock createStock() {
        return repository.save(new Stock());
    }

    public Stock getStockById(Long id) {
        Optional<Stock> stock = repository.findById(id);
        return stock.orElseThrow(() -> new Error("Stock not found for ID: " + id));
    }
    public List<ProductResponseDTO> getProducts (Long id) {
        var stock = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Stock doesnt exists"));
       return stock.getProducts().stream().map(ProductResponseDTO::new).toList();

    }

    public Stock updateStock(Stock stock) {
        if (repository.findById(stock.getId()).isEmpty()) {
            throw new Error("Stock not found");
        }
        return repository.save(stock);
    }

    public Long updateStockProducts(Long stockId, List<UpdateProductStockDTO> products) {
        var stock = getStockById(stockId);

        stock.setProducts(updateProductsListById(products));
        return repository.save(stock).getId();
    }

    public boolean checkIfHasStock(int quantityProduct, int quantitySale) {
        return quantityProduct >= quantitySale;
    }

    public Product getProductIfExists(Stock stock, String productId) {
        return stock.getProducts().stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("Produto com ID " + productId + " não encontrado no estoque com ID: " + stock.getId()));
    }

    public List<Product> updateProductsListById (List<UpdateProductStockDTO> products) {
        var productList = new ArrayList<Product>();

        for (UpdateProductStockDTO product : products) {
            var productStocked = productService.getProductById(product.productId());
            this.productService.updateProduct(new CreateProductRequestDTO(productStocked));
        }

        return productList;
    }
}
