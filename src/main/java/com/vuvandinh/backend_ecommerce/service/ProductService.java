package com.vuvandinh.backend_ecommerce.service;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.Category;
import com.vuvandinh.backend_ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Page<Product> getAllProducts(Category category,Double min,Double max, Brand brand,String keyword, Pageable pageable);
    public Product geProductBySlug(String slug);
    public Product getById(Long id);

    public Product createProduct(Product product);
    public Product updateProduct( Product product);
    void deleteProduct(Long id);
//    public Page<Product> getProductsByCategoryId(Long categoryId, Pageable pageable);
    public Page<Product> getDealProducts( Pageable pageable);

    public Page<Product> getHotProducts( Pageable pageable);
    public Page<Product> getTopSellingProducts( Pageable pageable);
    Page<Product> getProductsByCategorys(Pageable pageable);


//    public List<Product> getAllProductsPagination(int pageNo,int pageSize);
}
