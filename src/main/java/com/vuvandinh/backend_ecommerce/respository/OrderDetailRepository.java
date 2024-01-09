package com.vuvandinh.backend_ecommerce.respository;

import com.vuvandinh.backend_ecommerce.entity.Order;
import com.vuvandinh.backend_ecommerce.entity.Order_details;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDetailRepository extends JpaRepository<Order_details, Long> {


}
