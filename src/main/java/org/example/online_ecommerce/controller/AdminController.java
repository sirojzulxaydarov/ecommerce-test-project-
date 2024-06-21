package org.example.online_ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.online_ecommerce.entity.Category;
import org.example.online_ecommerce.entity.Product;
import org.example.online_ecommerce.repo.CategoryRepo;
import org.example.online_ecommerce.repo.ProductRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    @GetMapping("/category")
    public String category(Model model) {
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "admin/category";
    }

    @GetMapping("/category/create")
    public String categoryCreat(Model model) {
        return "admin/create-category";
    }

    @GetMapping("/product")
    public String product(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "admin/product";
    }

    @GetMapping("/product/create")
    public String productCreat(Model model) {
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "admin/create-product";
    }


}
