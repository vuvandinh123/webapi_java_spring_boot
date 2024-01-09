package com.vuvandinh.backend_ecommerce.controllers;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.Role;
import com.vuvandinh.backend_ecommerce.entity.User;
import com.vuvandinh.backend_ecommerce.respository.RoleRepository;
import com.vuvandinh.backend_ecommerce.service.BrandService;
import com.vuvandinh.backend_ecommerce.service.RoleService;
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
@RequestMapping("api/roles")
public class RoleController {
    private RoleService service;

    @GetMapping("")
    public ResponseEntity<Page<Role>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable;
        if (page > 0) {
            --page;
        }
        pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "id");
        Page<Role> role = service.getAll(pageable);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>("Category successfully deleted!", HttpStatus.OK);
    }

}
