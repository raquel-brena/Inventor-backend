package com.rb.auth.services;

import com.rb.auth.controllers.SaleController;
import com.rb.auth.controllers.StockController;
import com.rb.auth.controllers.UserController;
import com.rb.auth.domain.address.Address;
import com.rb.auth.domain.order.CreateOrderDTO;
import com.rb.auth.domain.product.CreateProductRequestDTO;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.product.SaleProductDTO;
import com.rb.auth.domain.sale.CreatedSaleDTO;
import com.rb.auth.domain.stock.Stock;
import com.rb.auth.domain.store.CreateStoreResponseDTO;
import com.rb.auth.domain.store.Store;
import com.rb.auth.repositories.AddressRepository;
import com.rb.auth.repositories.StoreRepository;
import com.rb.auth.services.AddressService;
import com.rb.auth.services.SaleService;
import com.rb.auth.services.store.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StoreService implements IStoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    UserController userController;

    @Autowired
    AddressService addressService;

    @Autowired
    SaleController saleController;

    @Autowired
    StockController stockController;

    public String createStore (CreateStoreResponseDTO dto) {
        System.out.println(dto.address().state());
        Address address = this.addressService.createAddress(dto.address());

        if (this.storeRepository.findByName(dto.name()).isPresent()) {
            throw new Error("Store already exists");
        }
        Stock stock = this.stockController.createStock();

        var store = new Store();
        store.setAddress(address);
        store.setName(dto.name());
        store.setStock(stock);

        var storeCreated = this.storeRepository.save(store);

        stock.setStore(storeCreated);
        address.setStore(storeCreated);

        this.stockController.updateStock(stock);
        this.addressService.updateAddress(address);

        return storeCreated.getId();
    }

    @Override
    public void generateInvetoryReport() {
    }


    public Long stockProducts(String storeId, List<CreateProductRequestDTO> products) {
        var store = this.storeRepository.findById(storeId);
        if (store.isEmpty()) {
            throw new Error("Store doesnt exist");
        }

        return this.stockController.stockProducts(store.get().getStock(), products);

    }

    @Override
    public String processSale(CreatedSaleDTO saleDTO) {
        Optional<Store> store = this.storeRepository.findById(saleDTO.storeId());

        if (store.isEmpty()) throw new Error("Store doesnt exists");

        var stock = store.get().getStock();

        var author = this.userController.getUserById(saleDTO.authorId());

        var sale = this.saleController.createNewSale(stock, saleDTO.productsDTO(), author, saleDTO.discount());

        store.get().getSales().add(sale);

        return "";
    }

    @Override
    public String restockProduct(String storeId,String productId, int quantity) {
        return null;
    }

    @Override
    public Product findProductByName(String storeId,String productId) {
        return null;
    }




}
