package com.vuvandinh.backend_ecommerce.service.Impl;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.ProductImage;
import com.vuvandinh.backend_ecommerce.respository.BrandRepository;
import com.vuvandinh.backend_ecommerce.respository.ImageRepository;
import com.vuvandinh.backend_ecommerce.service.BrandService;
import com.vuvandinh.backend_ecommerce.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private ImageRepository imageRepository;


    @Override
    public ProductImage upload(ProductImage image) {
        return imageRepository.save(image);
    }

    @Override
    public List<ProductImage> uploadAll(List<ProductImage> images) {
        return imageRepository.saveAll(images);
    }

    @Override
    public ProductImage getById(Long id) {
        return null;
    }

    @Override
    public List<ProductImage> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }
}