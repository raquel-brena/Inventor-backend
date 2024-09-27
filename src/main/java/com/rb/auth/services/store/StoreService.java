package com.rb.auth.services.store;

import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.stock.Stock;
import com.rb.auth.domain.store.CreateStoreResponseDTO;
import com.rb.auth.domain.store.Store;
import com.rb.auth.repositories.ProductRepository;
import com.rb.auth.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.Optional;


@Service
public class StoreService implements IStoreService{

    @Autowired
    StoreRepository storeRepository;

    @Override
    public void generateInvetoryReport() {
    }

    @Override
    public String processSale(String storeId, String productId, int quantity) {
        Optional<Store> store = storeRepository.findById(storeId);

        if (store.isEmpty()) throw new Error("Store doesnt exists");

        var stock = store.get().getStockId();

        var products = stock.getProducts();


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

    public String createStore (CreateStoreResponseDTO body) {
        if (this.storeRepository.findByName(body.name()).isPresent()) {
            throw new Error("Store already exists");
        }
        var newStore = this.storeRepository.save(new Store(body));
        return newStore.getId();
    }
}
