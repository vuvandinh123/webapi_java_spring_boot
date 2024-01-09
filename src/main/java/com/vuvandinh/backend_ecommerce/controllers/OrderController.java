package com.vuvandinh.backend_ecommerce.controllers;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.Order;
import com.vuvandinh.backend_ecommerce.entity.Order_details;
import com.vuvandinh.backend_ecommerce.service.BrandService;
import com.vuvandinh.backend_ecommerce.service.OrderDetailService;
import com.vuvandinh.backend_ecommerce.service.OrderService;
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
@RequestMapping("api/orders")
public class OrderController {
    private OrderService orderService;
    private OrderDetailService orderDetailService;
    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        Order saved = orderService.create(order);
        orderDetailService.create(order.getOrderDetails());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<Page<Order>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable;
        if (page > 0) {
            --page;
        }
        pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "id");
        Page<Order> order = orderService.getAll(pageable);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
