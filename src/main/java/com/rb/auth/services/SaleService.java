package com.rb.auth.services;

import com.rb.auth.domain.order.Order;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.product.UpdateProductStockDTO;
import com.rb.auth.domain.sale.Sale;
import com.rb.auth.domain.stock.Stock;
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
    OrderService orderService;

    @Autowired
    SaleRepository saleRepository;


    @Transactional
    public Sale createNewSale(List<Product> products, User author, int discount) {
        var orders = this.processOrders(products, products);
        var newSale = new Sale();

        var totalWithDiscount = calculateTotal(orders, discount);

        newSale.setAuthor(author);
        newSale.setOrders(orders);
        newSale.setClosed(true);
        newSale.setTotal(totalWithDiscount);


        return this.saleRepository.save(newSale);
    }

    public List<Order> processOrders(List<Product> products, List<UpdateProductStockDTO> productsDTO) {
        List<Order> orders = new ArrayList<>();

        for (UpdateProductStockDTO productDTO : productsDTO) {
            var product = this.getProductsFromStock(stock, productDTO);
            var newOrder = this.orderService.createOrder(product, productDTO.quantity());
            orders.add(newOrder);
        }
        return orders;

    }

    public Product getProductsFromStock(List<Product> stock,UpdateProductStockDTO dto) {
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
