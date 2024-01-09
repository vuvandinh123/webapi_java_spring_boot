package com.vuvandinh.backend_ecommerce.service.Impl;

import com.vuvandinh.backend_ecommerce.entity.User;
import com.vuvandinh.backend_ecommerce.respository.UserRepository;
import com.vuvandinh.backend_ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        user.setCreatedAt(new Date());
        user.setTotalMoney(0.0);
        return userRepository.save(user);
    }

    @Override
    public User getById(Long categoryId) {
        Optional<User> optionalCategory = userRepository.findById(categoryId);
        return optionalCategory.get();
    }


    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User update(User user) {
        User existing = userRepository.findById(user.getId()).get();
        existing.setName(user.getName());
        existing.setStatus(user.getStatus());
        existing.setUpdatedAt(user.getUpdatedAt());
        existing.setAddress(user.getAddress());
        existing.setPhone(user.getPhone());
        existing.setEmail(user.getEmail());
        existing.setUpdatedAt(new Date());
        existing.setRole(user.getRole());
        User updatedCategory = userRepository.save(existing);
        return updatedCategory;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}