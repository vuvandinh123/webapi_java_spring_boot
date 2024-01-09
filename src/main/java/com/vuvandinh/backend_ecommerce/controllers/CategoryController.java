package com.vuvandinh.backend_ecommerce.controllers;

import com.vuvandinh.backend_ecommerce.entity.Category;
import com.vuvandinh.backend_ecommerce.entity.Product;
import com.vuvandinh.backend_ecommerce.service.CategoryService;
import com.vuvandinh.backend_ecommerce.service.ProductService;
import com.vuvandinh.backend_ecommerce.service.ResponseObject;
import com.vuvandinh.backend_ecommerce.until.SetSlug;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private CategoryService categoryService;
    private static final String UPLOAD_DIR = "static/images/";

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestParam("image") MultipartFile file,
                                                   @RequestParam("name") String name,
                                                   @RequestParam("description") String description,
                                                   @RequestParam("status") Integer status)
            throws IOException {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + extension;
        Path path = Paths.get(UPLOAD_DIR + newFilename);
        Files.write(path, file.getBytes());

        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        category.setStatus(status);
        category.setSlug(SetSlug.generateSlug(name));
        category.setImage(newFilename);

        Category savedCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("{slug}")
    public ResponseEntity<Category> getCategoryBySlug(@PathVariable("slug") String categoryyId) {
        Category Category = categoryService.geCategoryBySlug(categoryyId);
        return new ResponseEntity<>(Category, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<Page<Category>>getAllCategorys(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        Pageable pageable;
        if (page > 0) {
            --page;
        }
        pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "id");
        Page<Category> Categories=categoryService.getAllCategories(pageable);
        return new ResponseEntity<>(Categories, HttpStatus.OK);
    }
    @GetMapping("{id}/products")
    public ResponseEntity<Page<Category>>getProductsByCategorys(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        Pageable pageable;
        if (page > 0) {
            --page;
        }
        pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "id");
        Page<Category> Categories=categoryService.getAllCategories(pageable);
        return new ResponseEntity<>(Categories, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Category>updateCategory(@PathVariable("id") Long categoryId,
                                                  @RequestParam(value = "image", required = false) MultipartFile file,
                                                  @RequestParam("name") String name,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("status") Integer status)
            throws IOException {
        String newFilename = null;
        if (file != null && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            newFilename = UUID.randomUUID().toString() + extension;
            Path path = Paths.get(UPLOAD_DIR + newFilename);
            Files.write(path, file.getBytes());
        }

        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        category.setStatus(status);
        category.setSlug(SetSlug.generateSlug(name));
        category.setImage(newFilename);
        category.setId(categoryId);
        Category updatedCategory=categoryService.updateCategory(category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteCategory(@PathVariable("id")Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category successfully deleted!", HttpStatus.OK);
    }

}
