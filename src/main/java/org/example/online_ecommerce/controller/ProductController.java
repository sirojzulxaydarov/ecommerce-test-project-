package org.example.online_ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.online_ecommerce.AttachmentRepo;
import org.example.online_ecommerce.dto.ProductReq;
import org.example.online_ecommerce.entity.Attachment;
import org.example.online_ecommerce.entity.Category;
import org.example.online_ecommerce.entity.Product;
import org.example.online_ecommerce.repo.CategoryRepo;
import org.example.online_ecommerce.repo.ProductRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/product")
@Controller
@RequiredArgsConstructor
public class ProductController {
    private final CategoryRepo categoryRepo;
    private final AttachmentRepo attachmentRepo;
    private final ProductRepo productRepo;

    @PostMapping
    public String saveProduct(@ModelAttribute ProductReq productReq, @RequestParam MultipartFile file) throws IOException {
        Category category = categoryRepo.findById(productReq.categoryId()).orElseThrow(() -> new RuntimeException("category not found"));
        Attachment attachment = attachmentRepo.save(new Attachment(file.getBytes()));
        Product product = new Product(
                productReq.name(),
                productReq.price(),
                category,
                attachment,
                false
        );
        productRepo.save(product);
        return "redirect:/admin/product";
    }
}
