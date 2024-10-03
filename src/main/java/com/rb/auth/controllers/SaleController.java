package com.rb.auth.controllers;


import com.rb.auth.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SaleController {

    @Autowired
    SaleService saleService;


}
