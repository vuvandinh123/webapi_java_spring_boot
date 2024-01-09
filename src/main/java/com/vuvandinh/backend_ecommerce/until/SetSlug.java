package com.vuvandinh.backend_ecommerce.until;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class SetSlug {
    public static String generateSlug(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String slug = pattern.matcher(normalized).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("[^a-z0-9-]", "");
        return slug;
    }
}
