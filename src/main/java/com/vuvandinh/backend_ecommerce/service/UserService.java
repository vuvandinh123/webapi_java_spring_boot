package com.vuvandinh.backend_ecommerce.service;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {
    User create(User user);

    User getById(Long id);


    Page<User> getAll(Pageable pageable);

    User update(User user);


    void delete(Long Id);

}
