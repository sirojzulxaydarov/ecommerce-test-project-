package org.example.online_ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.online_ecommerce.entity.Category;
import org.example.online_ecommerce.repo.CategoryRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/category")
@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepo categoryRepo;

    @PostMapping
    public String saveCategory(@RequestParam String name) {
        Category category = new Category(name);
        categoryRepo.save(category);
        return "redirect:/admin/category";
    }
}
