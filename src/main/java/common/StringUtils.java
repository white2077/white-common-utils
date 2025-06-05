package common;

import java.text.Normalizer;
import java.util.Locale;

public class StringUtils {
    private StringUtils() {
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static String toSlug(String input) {
        if (input == null || input.isBlank()) return "";
        String slug = input.toLowerCase(Locale.ROOT);
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
        slug = slug.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        slug = slug.replaceAll("[^a-z0-9\\s-]", "");
        slug = slug.replaceAll("[\\s-]+", "-");
        slug = slug.replaceAll("^-|-$", "");
        return slug;
    }

}
