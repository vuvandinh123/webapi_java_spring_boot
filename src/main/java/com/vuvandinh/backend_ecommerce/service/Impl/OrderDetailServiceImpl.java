package com.vuvandinh.backend_ecommerce.service.Impl;

import com.vuvandinh.backend_ecommerce.entity.Order;
import com.vuvandinh.backend_ecommerce.entity.Order_details;
import com.vuvandinh.backend_ecommerce.respository.OrderDetailRepository;
import com.vuvandinh.backend_ecommerce.respository.OrderRepository;
import com.vuvandinh.backend_ecommerce.service.OrderDetailService;
import com.vuvandinh.backend_ecommerce.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailRepository orderDetailRepository;


    @Override
    public List<Order_details> create(List<Order_details> orderDetails) {
        return orderDetailRepository.saveAll(orderDetails);
    }
}