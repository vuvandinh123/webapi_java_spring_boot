package com.vuvandinh.backend_ecommerce.service;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {
    Order create(Order order);

    Order getById(Long id);
    Order geBySlug(String slug);

    Page<Order> getAll(Pageable pageable);

    Order update(Order order);


    void delete(Long Id);

}
