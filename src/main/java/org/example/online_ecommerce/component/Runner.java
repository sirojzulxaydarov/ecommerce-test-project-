package org.example.online_ecommerce.component;

import lombok.RequiredArgsConstructor;
import org.example.online_ecommerce.entity.Category;
import org.example.online_ecommerce.repo.CategoryRepo;
import org.example.online_ecommerce.repo.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class Runner implements CommandLineRunner {

    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    @Override
    public void run(String... args) throws Exception {
//        Category category1 = new Category("Yegulik");
//        Category category2 = new Category("Ichgulik");
//        categoryRepo.save(category1);
//        categoryRepo.save(category2);

    }
}
