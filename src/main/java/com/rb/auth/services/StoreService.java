package com.rb.auth.services;

import com.rb.auth.controllers.UserController;
import com.rb.auth.domain.address.Address;
import com.rb.auth.domain.enums.Status;
import com.rb.auth.domain.product.CreateProductRequestDTO;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.product.UpdateProductStockDTO;
import com.rb.auth.domain.sale.CreatedSaleDTO;
import com.rb.auth.domain.stock.Stock;
import com.rb.auth.domain.store.CreateStoreResponseDTO;
import com.rb.auth.domain.store.Store;
import com.rb.auth.repositories.StoreRepository;
import com.rb.auth.services.store.IStoreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StoreService implements IStoreService {

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
        System.out.println(dto.address().state());
        Address address = this.addressService.createAddress(dto.address());

        if (this.storeRepository.findByName(dto.name()).isPresent()) {
            throw new IllegalArgumentException("Store already exists");
        }

        var store = new Store();
        store.setAddress(address);
        store.setName(dto.name());

        var storeCreated = this.storeRepository.save(store);

        return storeCreated.getId();
    }

    @Override
    public void generateInvetoryReport() {
    }


    public Long updateStockProducts(String storeId, List<UpdateProductStockDTO> products) {
        var store = this.storeRepository.findById(storeId);

        if (store.isEmpty()) {
            throw new Error("Store doesnt exist");
        }

        return this.stockService.updateStockProducts(store.get().getStock(), products);

    }

    @Override
    @Transactional
    public String processSale(CreatedSaleDTO saleDTO) {
        var store = this.storeRepository.findById(saleDTO.storeId())
                .orElseThrow(() -> new IllegalArgumentException("Store doesnt exists"));

        var author = this.userController.getUserById(saleDTO.authorId());


        var productsForSale = new ArrayList<Product>();

        for (UpdateProductStockDTO pDTO : saleDTO.productsDTO()) {
            var p = this.productService.getProductById(pDTO.productId());

            if ((p.getStock().getOnHand() >= pDTO.quantity()))
                throw new IllegalArgumentException("Quantity dont valid");
            productsForSale.add(p);

            var newQuantityOnHand = p.getStock().getOnHand() - pDTO.quantity();
            var newQuantityToBePacked = p.getStock().getToBePacked() + pDTO.quantity();

            var status = "";
            if (newQuantityOnHand == 0) status == Status.INACTIVE.getStatus();
            var stock = p.getStock();

            stock.setOnHand(newQuantityOnHand);
            stock.setToBePacked(newQuantityToBePacked);

            var sale = this.saleService
                    .createNewSale(store.getStock(), saleDTO.productsDTO(), author, saleDTO.discount());

            //
            store.getSales().add(sale);

            return "";
        }
    }

    public Store getStore(String id) {
       return this.storeRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("Store doesnt exists"));
    }

    @Override
    public String restockProduct(String storeId, String productId, int quantity) {
        return null;
    }

    @Override
    public Product findProductByName(String storeId, String productId) {
        return null;
    }

    public Stock getStock(String storeId) {
        var store = this.storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store doesnt exists"));

        var stock = store.getStock();
        if (stock == null) {
            throw new IllegalArgumentException("No stock found for store with ID " + storeId);
        }

        return stock;
    }


}
