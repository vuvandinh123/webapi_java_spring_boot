package com.vuvandinh.backend_ecommerce.service.Impl;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.Category;
import com.vuvandinh.backend_ecommerce.entity.Product;
import com.vuvandinh.backend_ecommerce.respository.ProductRepository;
import com.vuvandinh.backend_ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productex = productRepository.findById(product.getId()).get();
        productex.setName(product.getName());
        productex.setDescription(product.getDescription());
//        productex.setImage("image");
        productex.setCategory(product.getCategory());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product geProductBySlug(String slug) {
        Optional<Product> optionalProduct = productRepository.findBySlug(slug);
        return optionalProduct.get();
    }

    @Override
    public Product getById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.get();
    }

    @Override
    public Page<Product> getAllProducts(Category category,Double min,Double max, Brand brand,String keyword, Pageable pageable) {

        if (brand != null && category != null) {
            return productRepository.findByCategoryAndBrand(category, brand, pageable);
        } else if (brand != null) {
             return productRepository.findByBrand(brand,pageable);
        } else if (category != null) {
             return productRepository.findByCategory(category,pageable);
        }
//        if((keyword != null && category != null)) {
//            return productRepository.findByTitleContainingAndCategory_Id(keyword,category.getId(),pageable);
//        }
        if(keyword != null) {
            return productRepository.findByNameContaining(keyword,pageable);
        }
        if(min != 0 && max != 0) {
            return productRepository.findByPriceBetween(min,max,pageable);
        }
        return productRepository.findAll(pageable);

    }

//    @Override
//    public Page<Product> getProductsByCategoryId(Long categoryId, Pageable pageable) {
//        return productRepository.findByCategory_Id(categoryId, pageable);
//    }

    @Override
    public Page<Product> getDealProducts(Pageable pageable) {
        return productRepository.findByFeature(2,pageable);
    }



    @Override
    public Page<Product> getHotProducts(Pageable pageable) {
        return productRepository.findByFeature(1,pageable);
    }

    @Override
    public Page<Product> getTopSellingProducts(Pageable pageable) {
        return productRepository.findByFeature(3,pageable);
    }

    @Override
    public Page<Product> getProductsByCategorys(Pageable pageable) {
        return null;
    }
}
