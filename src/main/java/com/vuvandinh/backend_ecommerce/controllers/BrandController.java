package com.vuvandinh.backend_ecommerce.controllers;

import com.vuvandinh.backend_ecommerce.entity.Brand;
import com.vuvandinh.backend_ecommerce.service.BrandService;
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
@RequestMapping("api/brands")
public class BrandController {
    private BrandService brandService;

    @PostMapping
    public ResponseEntity<Brand> create(@RequestParam("image") MultipartFile file,
                                        @RequestParam("name") String name,
                                        @RequestParam("status") Integer status) throws IOException {

        String newFilename = Upload.generateImage(file);

        Brand brand = new Brand();
        brand.setName(name);
        brand.setStatus(status);
        brand.setSlug(SetSlug.generateSlug(name));
        brand.setImage(newFilename);

        Brand savedCategory = brandService.create(brand);

        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    //    @GetMapping("{id}")
//    public ResponseEntity<Brand> getCategoryById(@PathVariable("id") Long id) {
//        Brand brand = brandService.getById(id);
//        return new ResponseEntity<>(brand, HttpStatus.OK);
//    }
    @GetMapping("{slug}")
    public ResponseEntity<Brand> getBySlug(@PathVariable("slug") String slug) {
        Brand brand = brandService.geBySlug(slug);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<Brand>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable;
        if (page > 0) {
            --page;
        }
        pageable = PageRequest.of(page, size, Sort.Direction.fromString("desc"), "id");
        Page<Brand> brand = brandService.getAll(pageable);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Brand> update(@PathVariable("id") Long id,
                                        @RequestParam(value = "image", required = false) MultipartFile file,
                                        @RequestParam("name") String name,
                                        @RequestParam("status") Integer status) throws IOException {
        String newFilename = Upload.generateImage(file);
        Brand brand = new Brand();
        brand.setImage(newFilename);
        brand.setName(name);
        brand.setSlug(SetSlug.generateSlug(name));
        brand.setStatus(status);
        brand.setId(id);
        Brand updatedCategory = brandService.update(brand);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        brandService.delete(id);
        return new ResponseEntity<>("Category successfully deleted!", HttpStatus.OK);
    }

}
