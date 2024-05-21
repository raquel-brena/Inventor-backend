package com.rb.auth.repositories;

import com.rb.auth.domain.product.Category;
import com.rb.auth.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findById(String id);
    Optional<Product> findBySku(String sku);
   List<Product> findByCategory(Category category);

}
