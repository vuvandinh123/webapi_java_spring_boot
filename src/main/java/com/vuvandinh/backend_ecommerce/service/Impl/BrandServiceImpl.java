package com.vuvandinh.backend_ecommerce.service.Impl;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.Category;
import com.vuvandinh.backend_ecommerce.respository.BrandRepository;
import com.vuvandinh.backend_ecommerce.respository.CategoryRepository;
import com.vuvandinh.backend_ecommerce.service.BrandService;
import com.vuvandinh.backend_ecommerce.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRespository;

    @Override
    public Brand create(Brand brand) {
        return brandRespository.save(brand);
    }

    @Override
    public Brand getById(Long categoryId) {
        Optional<Brand> optionalCategory = brandRespository.findById(categoryId);
        return optionalCategory.get();
    }

    @Override
    public Brand geBySlug(String slug) {
        Optional<Brand> optional = brandRespository.findBySlug(slug);
        return optional.get();
    }

    @Override
    public Page<Brand> getAll(Pageable pageable) {
        return brandRespository.findAll(pageable);
    }

    @Override
    public Brand update(Brand brand) {
        Brand existing = brandRespository.findById(brand.getId()).get();
        existing.setName(brand.getName());
        if(brand.getImage() != null){
            existing.setImage(brand.getImage());
        }
        existing.setStatus(brand.getStatus());
        existing.setSlug(brand.getSlug());
        existing.setUpdatedAt(new Date());
        Brand updatedCategory = brandRespository.save(existing);
        return updatedCategory;
    }

    @Override
    public void delete(Long id) {
        brandRespository.deleteById(id);
    }
}