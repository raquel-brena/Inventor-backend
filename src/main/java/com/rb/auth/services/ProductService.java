package com.rb.auth.services;

import com.rb.auth.domain.product.CreateProductRequestDTO;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.product.ProductResponseDTO;
import com.rb.auth.domain.stock.Stock;
import com.rb.auth.repositories.ProductRepository;
import com.rb.auth.services.exceptions.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    StockService stockService;

    public Product createProduct(CreateProductRequestDTO dto) {
        var product = this.repository.findBySku(dto.sku());

        if (product.isPresent())
            throw new IllegalArgumentException("Product already exists: " + product.get().getName() + " sku: " + product.get().getSku());
        var stock = this.stockService.createStock();
        return this.repository.save(new Product(dto, stock));
    }


    public Product updateProduct(CreateProductRequestDTO dto) {
        var product = repository.findBySku(dto.sku())
                .orElseThrow(() -> new IllegalArgumentException("Product doesn't exist"));

        var stock = product.getStock();
        var updatedProduct = this.toProductMapper(dto, stock);

        return this.repository.save(updatedProduct);
    }

    public void save(Product product) {
        this.repository.save(product);
    }

    public List<ProductResponseDTO> getAll() {
        return this.repository.findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();
    }

    public Product getProductById(String id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product toProductMapper(CreateProductRequestDTO dto, Stock stock) {
        var product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setVariants(dto.variants());
        product.setGender(dto.gender());
        product.setRetail_price(dto.retail_price());
        product.setWholesale_price(dto.wholesale_price());
        product.setSku(dto.sku());
        product.setStatus(dto.status());
        product.setBarcode(dto.barcode());
        product.setUnit(dto.unit());
        product.setCategory(dto.category());
        product.setStock(stock);

        stock.setOnHand(dto.onHand());
        stock.setToBeReceived(dto.toBeReceived());
        stock.setToBePacked(dto.toBePacked());

        return product;
    }
}