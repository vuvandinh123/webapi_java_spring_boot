package com.vuvandinh.backend_ecommerce.service;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RoleService {
    Role create(Role role);

    Role getById(Long id);

    Page<Role> getAll(Pageable pageable);

    Role update(Role role);


    void delete(Long Id);

}
