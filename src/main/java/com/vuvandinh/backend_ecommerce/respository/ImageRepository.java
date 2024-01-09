package com.vuvandinh.backend_ecommerce.respository;

import com.vuvandinh.backend_ecommerce.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<ProductImage, Long> {


}
