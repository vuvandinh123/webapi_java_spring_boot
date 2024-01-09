package com.vuvandinh.backend_ecommerce.until;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.UUID;
import java.util.regex.Pattern;

public class Upload {
    private static final String UPLOAD_DIR = "static/images/";

    public static String generateImage(MultipartFile file) throws IOException {
        String newFilename = null;
        if (file != null && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            newFilename = UUID.randomUUID().toString() + extension;
            Path path = Paths.get(UPLOAD_DIR + newFilename);
            Files.write(path, file.getBytes());
        }
        return newFilename;
    }
}
