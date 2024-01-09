package com.vuvandinh.backend_ecommerce.service;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.Order_details;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface OrderDetailService {
    List<Order_details> create(List<Order_details> orderDetails);



}
