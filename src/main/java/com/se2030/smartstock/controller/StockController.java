package com.se2030.smartstock.controller;

import com.se2030.smartstock.model.Stock;
import com.se2030.smartstock.service.ProductService;
import com.se2030.smartstock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listStocks(Model model) {
        model.addAttribute("stocks", stockService.getAllStocks());
        return "stock/list";
    }

    @GetMapping("/new")
    public String newStockForm(Model model) {
        model.addAttribute("stock", new Stock());
        model.addAttribute("products", productService.getAllProducts());
        return "stock/form";
    }

    @GetMapping("/edit/{id}")
    public String editStockForm(@PathVariable Long id, Model model) {
        model.addAttribute("stock", stockService.getStockById(id));
        model.addAttribute("products", productService.getAllProducts());
        return "stock/form";
    }

    @PostMapping("/save")
    public String saveStock(@ModelAttribute Stock stock) {
        stockService.saveStock(stock);
        return "redirect:/stocks";
    }

    @GetMapping("/delete/{id}")
    public String deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return "redirect:/stocks";
    }
}
