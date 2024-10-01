package com.rb.auth.services;

import com.rb.auth.domain.order.Order;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.product.UpdateProductStockDTO;
import com.rb.auth.domain.sale.Sale;
import com.rb.auth.domain.stock.Stock;
import com.rb.auth.domain.user.User;
import com.rb.auth.repositories.SaleRepository;
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

    public Sale createNewSale(Stock stock, List<UpdateProductStockDTO> productsDTO, User author, int discount) {
        var orders = this.processOrders(stock, productsDTO);
        var newSale = new Sale();

        var totalWithDiscount = calculateTotal(orders, discount);

        newSale.setAuthor(author);
        newSale.setOrders(orders);
        newSale.setClosed(true);
        newSale.setTotal(totalWithDiscount);


        return this.saleRepository.save(newSale);
    }

    public List<Order> processOrders(Stock stock, List<UpdateProductStockDTO> productsDTO) {
        List<Order> orders = new ArrayList<>();

        for (UpdateProductStockDTO productDTO : productsDTO) {
            var product = this.getProductsFromStock(stock, productDTO);
            var newOrder = this.orderService.createOrder(product, productDTO.quantity());
            orders.add(newOrder);
        }
        return orders;

    }

    public Product getProductsFromStock(Stock stock, UpdateProductStockDTO dto) {
        //pegar os produtos do estoque e verificar se o contain o id informado
        var productsStock = stock.getProducts();
        for (Product p : productsStock) {
            if (!(p.getId().equals(dto.productId()))) throw new IllegalArgumentException("");
            if (p.getOnHand() <= dto.quantity()) throw new IllegalArgumentException("");
            return p;

        }
    }

    public float calculateTotal(List<Order> orders, int discount) {
        float total = 0;
        for (Order order : orders) {
            total = order.getTotalPrice() + total;
        }
        return total - ((total * discount) / 100);
    }
}
