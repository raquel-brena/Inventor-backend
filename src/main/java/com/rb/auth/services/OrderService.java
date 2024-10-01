package com.rb.auth.services;

import com.rb.auth.domain.order.Order;
import com.rb.auth.domain.product.Product;
import com.rb.auth.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order createOrder(Product product, int quantity) {
        var totalOrder = product.getRetail_price() * quantity;

        var order = new Order();
        order.setQuantity(quantity);
        order.setProduct(product);
        order.setTotalPrice(totalOrder);

        this.orderRepository.save(order);

        return order;
    }

}
