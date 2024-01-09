package com.vuvandinh.backend_ecommerce.controllers;


import com.vuvandinh.backend_ecommerce.entity.Category;
import com.vuvandinh.backend_ecommerce.entity.Product;
import com.vuvandinh.backend_ecommerce.entity.ProductImage;
import com.vuvandinh.backend_ecommerce.service.CategoryService;
import com.vuvandinh.backend_ecommerce.service.ImageService;
import com.vuvandinh.backend_ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(path = "api/images")
public class ImageController {
    private static final String UPLOAD_DIR = "static/images/";
    private ImageService productImageService;
    private ProductService productService;

    private static final String IMAGE_DIR = "static/images/";

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            File file = new File(IMAGE_DIR + imageName);
            if (file.exists()) {
                Resource resource = new FileSystemResource(file);
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Kiểu dữ liệu của ảnh
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
//    @PostMapping("/upload")
//    public ResponseEntity<ProductImage> uploadImage( @RequestParam("image") MultipartFile file,@RequestParam("productId") Long productId) {
//        try {
//            String originalFilename = file.getOriginalFilename();
//            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//            String newFilename = UUID.randomUUID().toString() + extension;
//            Path path = Paths.get(UPLOAD_DIR + newFilename);
//            Files.write(path, file.getBytes());
//            return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImages(@RequestParam("images") List<MultipartFile> files, @RequestParam("productId") Long productId) {
        try {
            // Kiểm tra sự tồn tại của sản phẩm
            Product product = productService.getById(productId);
            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Product with ID " + productId + " not found.");
            }

            List<ProductImage> uploadedImages = new ArrayList<>();

            for (MultipartFile file : files) {
                String originalFilename = file.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                if (!extension.matches("\\.(jpg|jpeg|png|gif|webp)$")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Invalid file format. Supported formats: jpg, jpeg, png, gif");
                }

                String newFilename = UUID.randomUUID().toString() + extension;
                Path path = Paths.get(UPLOAD_DIR + newFilename);
                Files.write(path, file.getBytes());

                ProductImage productImage = new ProductImage();
                productImage.setImageUrl(newFilename);
                productImage.setProduct(product);
                uploadedImages.add(productImage);
            }

            List<ProductImage> savedImages = productImageService.uploadAll(uploadedImages);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedImages);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the files.");
        }
    }

}
