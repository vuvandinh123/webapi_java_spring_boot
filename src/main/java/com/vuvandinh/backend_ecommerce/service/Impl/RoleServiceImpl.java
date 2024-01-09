package com.vuvandinh.backend_ecommerce.service.Impl;

import com.vuvandinh.backend_ecommerce.entity.Role;
import com.vuvandinh.backend_ecommerce.respository.RoleRepository;
import com.vuvandinh.backend_ecommerce.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRespository;


    @Override
    public Role create(Role role) {
        return null;
    }

    @Override
    public Role getById(Long id) {
        return null;
    }

    @Override
    public Page<Role> getAll(Pageable pageable) {
        return roleRespository.findAll(pageable);
    }

    @Override
    public Role update(Role role) {
        return null;
    }


    @Override
    public void delete(Long id) {
        roleRespository.deleteById(id);
    }
}