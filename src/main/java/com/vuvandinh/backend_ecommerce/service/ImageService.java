package com.vuvandinh.backend_ecommerce.service;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.ProductImage;

import java.util.List;


public interface ImageService {
    ProductImage upload(ProductImage image);
    List<ProductImage> uploadAll(List<ProductImage> image);


    ProductImage getById(Long id);

    List<ProductImage> getAll();



    void delete(Long Id);

}
