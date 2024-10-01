package com.rb.auth.services.store;

import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.sale.CreatedSaleDTO;

public interface IStoreService {

    void generateInvetoryReport();

    String processSale(CreatedSaleDTO createdSaleDTO);


    String restockProduct(String storeId, String productId, int quantity);

    Product findProductByName(String storeId, String productId);


   /*
+ generateInventoryReport(): Report;
+ processSale(productId: string, quantity: int): string;
+ addProduct(Product product): string;
+ restockProduct(productId: string, quantity: int): string;
+ updateInventory(Inventory inventory): string;
+ checkLowStock(threshold: int): List<Product>;
+ findProductByName(name: string): Product;*/


}
