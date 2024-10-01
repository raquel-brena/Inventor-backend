package com.rb.auth.controllers;


import com.rb.auth.domain.product.UpdateProductStockDTO;
import com.rb.auth.domain.sale.Sale;
import com.rb.auth.domain.stock.Stock;
import com.rb.auth.domain.user.User;
import com.rb.auth.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SaleController {

    @Autowired
    SaleService saleService;

    public Sale createNewSale(Stock stock, List<UpdateProductStockDTO> productsDTO, User author, int discount) {
        var sale = this.saleService.createNewSale(stock, productsDTO, author, discount);
        return sale;
    }


}
