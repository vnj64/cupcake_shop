package com.example.steamkey.repositories;

import com.example.steamkey.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);

    List<Product> findByTitleContainingOrDescriptionContaining(String keyword, String keyword1);
}
