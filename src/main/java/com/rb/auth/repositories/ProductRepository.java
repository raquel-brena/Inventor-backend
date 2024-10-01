package com.rb.auth.repositories;

import com.rb.auth.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @SuppressWarnings("null")
    Optional<Product> findById(String id);

    Optional<Product> findBySku(String sku);
    //List<Product> findByCategoryIn(Category category);
}
