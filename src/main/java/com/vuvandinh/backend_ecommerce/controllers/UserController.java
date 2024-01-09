package com.vuvandinh.backend_ecommerce.controllers;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.User;
import com.vuvandinh.backend_ecommerce.service.BrandService;
import com.vuvandinh.backend_ecommerce.service.UserService;
import com.vuvandinh.backend_ecommerce.until.SetSlug;
import com.vuvandinh.backend_ecommerce.until.Upload;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        User savedCategory = userService.create(user);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

        @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<Page<User>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable;
        if (page > 0) {
            --page;
        }
        pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "id");
        Page<User> user = userService.getAll(pageable);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@RequestBody User user){
        User updatedCategory = userService.update(user);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>("Category successfully deleted!", HttpStatus.OK);
    }

}
