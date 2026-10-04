package com.se2030.smartstock.controller;

import com.se2030.smartstock.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private StockService stockService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String landing() {
        return "landing";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("productCount", productService.getAllProducts().size());
        model.addAttribute("supplierCount", supplierService.getAllSuppliers().size());
        model.addAttribute("categoryCount", categoryService.getAllCategories().size());
        model.addAttribute("purchaseCount", purchaseService.getAllPurchases().size());
        model.addAttribute("stockCount", stockService.getAllStocks().size());
        model.addAttribute("userCount", userService.getAllUsers().size());
        return "dashboard";
    }
}
