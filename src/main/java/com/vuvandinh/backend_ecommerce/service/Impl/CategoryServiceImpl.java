package com.vuvandinh.backend_ecommerce.service.Impl;

import com.vuvandinh.backend_ecommerce.entity.Category;
import com.vuvandinh.backend_ecommerce.entity.Product;
import com.vuvandinh.backend_ecommerce.respository.CategoryRepository;
import com.vuvandinh.backend_ecommerce.respository.ProductRepository;
import com.vuvandinh.backend_ecommerce.service.CategoryService;
import com.vuvandinh.backend_ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRespository;

    @Override
    public Category createCategory(Category category) {
        return categoryRespository.save(category);
    }

    @Override
    public Category geCategoryBySlug(String slug) {
        Optional<Category> optional = categoryRespository.findBySlug(slug);
        return optional.get();
    }
    @Override
    public Category geCategoryById(Long id) {
        Optional<Category> optional = categoryRespository.findById(id);
        return optional.get();
    }
    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRespository.findAll(pageable);
    }

    @Override
    public Category updateCategory(Category category) {
        Category existingCategory = categoryRespository.findById(category.getId()).get();
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        if(category.getImage()!=null){
            existingCategory.setImage(category.getImage());
        }
        Category updatedCategory = categoryRespository.save(existingCategory);
        return updatedCategory;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRespository.deleteById(categoryId);
    }
}