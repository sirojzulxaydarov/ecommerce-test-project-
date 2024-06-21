package org.example.online_ecommerce.repo;

import org.example.online_ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
    List<Product> findAllByCategoryId(UUID categoryId);
}
