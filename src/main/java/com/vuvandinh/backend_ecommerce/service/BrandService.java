package com.vuvandinh.backend_ecommerce.service;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BrandService {
    Brand create(Brand brand);

    Brand getById(Long id);
    Brand geBySlug(String slug);

    Page<Brand> getAll(Pageable pageable);

    Brand update(Brand brand);


    void delete(Long Id);

}
