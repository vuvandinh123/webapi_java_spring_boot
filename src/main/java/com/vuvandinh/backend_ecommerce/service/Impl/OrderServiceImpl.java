package com.vuvandinh.backend_ecommerce.service.Impl;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.Order;
import com.vuvandinh.backend_ecommerce.respository.BrandRepository;
import com.vuvandinh.backend_ecommerce.respository.OrderRepository;
import com.vuvandinh.backend_ecommerce.service.BrandService;
import com.vuvandinh.backend_ecommerce.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;


    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return null;
    }

    @Override
    public Order geBySlug(String slug) {
        return null;
    }

    @Override
    public Page<Order> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}