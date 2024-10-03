package com.rb.auth.services;

import com.rb.auth.controllers.UserController;
import com.rb.auth.domain.address.Address;
import com.rb.auth.domain.product.CreateProductRequestDTO;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.sale.CreatedSaleDTO;
import com.rb.auth.domain.sale.Sale;
import com.rb.auth.domain.store.CreateStoreResponseDTO;
import com.rb.auth.domain.store.Store;
import com.rb.auth.repositories.StoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UserController userController;

    @Autowired
    AddressService addressService;

    @Autowired
    SaleService saleService;

    @Transactional
    public String createStore(CreateStoreResponseDTO dto) {
        Address address = this.addressService.createAddress(dto.address());

        if (this.storeRepository.findByName(dto.name()).isPresent()) {
            throw new IllegalArgumentException("Store already exist");
        }

        var store = new Store();
        store.setAddress(address);
        store.setName(dto.name());

        var storeCreated = this.storeRepository.save(store);

        return storeCreated.getId();
    }


    @Transactional
    public Sale processSale(CreatedSaleDTO saleDTO) {
        var store = this.storeRepository.findById(saleDTO.storeId())
                .orElseThrow(() -> new IllegalArgumentException("Store doesn't exist"));

        var author = this.userController.getUserById(saleDTO.authorId());


        var sale = this.saleService.createSale(saleDTO, author, saleDTO.discount(), store);

        store.getSales().add(sale);
        this.storeRepository.save(store);

        return sale;
    }


    public Store getStore(String id) {
        return this.storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Store doesnt exists"));
    }


    public String restockProduct(String storeId, String productId, int quantity) {
        return null;
    }


    public Product findProductByName(String storeId, String productName) {
        return getStore(storeId).getStock().stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product doesn't exist"));
    }

    public List<Product> getStock(String storeId) {
        return getStore(storeId).getStock();
    }

    @Transactional
    public String addProductToStock(String storeId, CreateProductRequestDTO productDTO) {
        Store store = getStore(storeId);

        var newProduct = this.productService.createProduct(productDTO);

        newProduct.setStore(store);

        store.getStock().add(newProduct);

        this.storeRepository.save(store);

        return newProduct.getId();
    }


    public void generateInvetoryReport() {
    }


//    public Long updateStockProducts(String storeId, List<UpdateProductStockDTO> products) {
//        var store = this.storeRepository.findById(storeId);
//
//        if (store.isEmpty()) {
//            throw new Error("Store doesnt exist");
//        }
//
//        return this.stockService.updateStockProducts(store.get().getStock(), products);
//
//    }


}
