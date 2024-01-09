package com.vuvandinh.backend_ecommerce.respository;

import com.vuvandinh.backend_ecommerce.entity.Category;
import com.vuvandinh.backend_ecommerce.entity.Brand;

import com.vuvandinh.backend_ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySlug(String slug);
    Page<Product> findByNameContaining(String name, Pageable pageable);
    Page<Product> findByCategoryAndBrand(Category category, Brand brand, Pageable pageable);
    Page<Product> findByBrand(Brand brand,Pageable pageable);
    Page<Product> findByCategory(Category category,Pageable pageable);
    Page<Product> findByFeature(Integer feature,Pageable pageable);
    Page<Product> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);


}
