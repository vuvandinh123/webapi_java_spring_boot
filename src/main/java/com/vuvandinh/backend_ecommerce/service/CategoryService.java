package com.vuvandinh.backend_ecommerce.service;

import com.vuvandinh.backend_ecommerce.entity.Category;
import com.vuvandinh.backend_ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface CategoryService {
    Category createCategory(Category category);

        Category geCategoryBySlug(String slug);
    Category geCategoryById(Long id);
    Page<Category> getAllCategories(Pageable pageable);

    Category updateCategory(Category category);
    // Boolean checkByPhoto(String photo);

    void deleteCategory(Long categoryId);

}
