package com.se2030.smartstock.controller;

import com.se2030.smartstock.model.Purchase;
import com.se2030.smartstock.service.ProductService;
import com.se2030.smartstock.service.PurchaseService;
import com.se2030.smartstock.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public String listPurchases(Model model) {
        model.addAttribute("purchases", purchaseService.getAllPurchases());
        return "purchase/list";
    }

    @GetMapping("/new")
    public String newPurchaseForm(Model model) {
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "purchase/form";
    }

    @GetMapping("/edit/{id}")
    public String editPurchaseForm(@PathVariable Long id, Model model) {
        model.addAttribute("purchase", purchaseService.getPurchaseById(id));
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "purchase/form";
    }

    @PostMapping("/save")
    public String savePurchase(@ModelAttribute Purchase purchase) {
        purchaseService.savePurchase(purchase);
        return "redirect:/purchases";
    }

    @GetMapping("/delete/{id}")
    public String deletePurchase(@PathVariable Long id) {
        purchaseService.deletePurchase(id);
        return "redirect:/purchases";
    }
}
