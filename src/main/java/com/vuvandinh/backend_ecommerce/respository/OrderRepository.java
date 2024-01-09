package com.vuvandinh.backend_ecommerce.respository;

import com.vuvandinh.backend_ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {


}
