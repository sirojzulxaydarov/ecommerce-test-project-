package org.example.online_ecommerce.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.online_ecommerce.entity.Attachment;
import org.example.online_ecommerce.entity.Product;
import org.example.online_ecommerce.repo.ProductRepo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class AttachmentController {

    private final ProductRepo productRepo;

    @Transactional
    @GetMapping("/product/{id}")
    public void returnProductImg(@PathVariable UUID id, HttpServletResponse response) throws IOException {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
        Attachment attachment = product.getAttachment();
        response.getOutputStream().write(attachment.getContent());

    }

}
