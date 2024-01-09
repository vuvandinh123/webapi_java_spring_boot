package com.vuvandinh.backend_ecommerce.controllers;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.entity.Category;
import com.vuvandinh.backend_ecommerce.entity.Product;
import com.vuvandinh.backend_ecommerce.service.BrandService;
import com.vuvandinh.backend_ecommerce.service.CategoryService;
import com.vuvandinh.backend_ecommerce.service.ProductService;
import com.vuvandinh.backend_ecommerce.until.SetSlug;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(path = "api/products")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private BrandService brandService;


    //    post
    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        product.setSlug(SetSlug.generateSlug(product.getName()));

        Product savedProduct = productService.createProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("{slug}")
    public ResponseEntity<Product> getById(@PathVariable("slug") String slug) {
        Product product = productService.geProductBySlug(slug);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        product.setSlug(SetSlug.generateSlug(product.getName()));
        Product update = productService.updateProduct(product);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("product successfully deleted!", HttpStatus.OK);
    }
    @GetMapping("/featured")
    public ResponseEntity<Page<Product>> getFeaturedProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable;
        if (page > 0) {
            --page;
        }
        pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "id");
        Page<Product> productPage = productService.getDealProducts(pageable);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
    @GetMapping("/hot")
    public ResponseEntity<Page<Product>> getHotProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable;
        if (page > 0) {
            --page;
        }
        pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "id");
        Page<Product> productPage = productService.getHotProducts(pageable);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
    @GetMapping("/search")
    public Page<Product> searchBooks(@RequestParam("keyword") String keyword) {

        return null;
    }
    @GetMapping("/topSelling")
    public ResponseEntity<Page<Product>> getTopSellingProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable;
        if (page > 0) {
            --page;
        }
        pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "id");
        Page<Product> productPage = productService.getTopSellingProducts(pageable);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(name = "categoryId" , required = false) Long categoryId,
            @RequestParam(name = "search" , required = false) String keyword,
            @RequestParam(name = "brandId" , required = false) Long brandId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "min", defaultValue = "0") Double min,
            @RequestParam(name = "max", defaultValue = "0") Double max,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy
    ) {
        Pageable pageable;
        if (page > 0) {
            --page;
        }
        if (sortBy.equals("priceAsc")) {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString("asc"), "price");
        } else if (sortBy.equals("priceDesc")) {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "price");
        } else if (sortBy.equals("newest")) {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "createdAt");
        } else if (sortBy.equals("oldest")) {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString("asc"), "createdAt");
        } else if (sortBy.equals("nameAsc")) {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString("asc"), "name");
        } else if (sortBy.equals("nameDesc")) {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "name");
        } else {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "id");
        }

        Category category = null;
        if (categoryId != null) {
            category = categoryService.geCategoryById(categoryId);
            if (category == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        Brand brand = null;
        if (brandId != null) {
            brand = brandService.getById(brandId);
            if (brand == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        Page<Product> productPage = productService.getAllProducts(category,min,max,brand,keyword,pageable);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }


}
