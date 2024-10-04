package com.rb.auth.services;

import com.rb.auth.domain.enums.Status;
import com.rb.auth.domain.order.Order;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.product.dto.UpdateProductStockDTO;
import com.rb.auth.domain.sale.dto.CreatedSaleDTO;
import com.rb.auth.domain.sale.Sale;
import com.rb.auth.domain.store.Store;
import com.rb.auth.domain.user.User;
import com.rb.auth.repositories.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    ProductService productService;

    @Autowired
    StoreService storeService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Transactional
    public Sale create(CreatedSaleDTO dto) {
        var store = this.storeService.getStore(dto.storeId());

        var author = this.userService.getUserById(dto.authorId());

        return this.processSale(dto, author, dto.discount(), store);
    }

    @Transactional
    public Sale processSale(CreatedSaleDTO saleDTO, User author, int discount, Store store) {

        List<Order> orders = new ArrayList<>();

        for (UpdateProductStockDTO pDTO : saleDTO.productsDTO()) {
            var product = this.productService.getProductById(pDTO.productId());

            if (product.getStock().getOnHand() < pDTO.quantity()) {
                throw new IllegalArgumentException("Invalid quantity for product: " + product.getName());
            }

            var newQuantityOnHand = product.getStock().getOnHand() - pDTO.quantity();
            var newQuantityToBePacked = product.getStock().getToBePacked() + pDTO.quantity();

            if (newQuantityOnHand == 0) {
                product.setStatus(Status.INACTIVE.getStatus());
            }

            var stock = product.getStock();
            stock.setOnHand(newQuantityOnHand);
            stock.setToBePacked(newQuantityToBePacked);

            var newOrder = this.orderService.createOrder(product, pDTO.quantity());
            orders.add(newOrder);

            this.productService.save(product);
        }

        var totalWithDiscount = calculateTotal(orders, discount);

        var newSale = new Sale(orders, author, store, totalWithDiscount, true);

        return this.saleRepository.save(newSale);
    }


    public Product getProductsFromStock(List<Product> stock, UpdateProductStockDTO dto) {
        var product = new Product();
        for (Product p : stock) {
            if (!(p.getId().equals(dto.productId()))) throw new IllegalArgumentException("");
            if (p.getStock().getOnHand() <= dto.quantity()) throw new IllegalArgumentException("");
            product = p;
        }
        return product;
    }

    public float calculateTotal(List<Order> orders, int discount) {
        float total = 0;
        for (Order order : orders) {
            total = order.getTotalPrice() + total;
        }
        return total - ((total * discount) / 100);
    }
}
