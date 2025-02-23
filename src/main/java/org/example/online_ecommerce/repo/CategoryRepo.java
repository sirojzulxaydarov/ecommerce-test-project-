package org.example.online_ecommerce.repo;

import org.example.online_ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepo extends JpaRepository<Category, UUID> {
}