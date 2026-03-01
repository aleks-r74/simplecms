package stream.lexlab.simplecms.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utils {
    private static final DateTimeFormatter TIMESTAMP_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMMdd_HH-mm-ss");

    public static String toSlug(String filename) {
        // Separate name and extension
        int dotIndex = filename.lastIndexOf('.');
        String name = (dotIndex == -1) ? filename : filename.substring(0, dotIndex);
        String ext = (dotIndex == -1) ? "" : filename.substring(dotIndex).toLowerCase();

        // Normalize name: lowercase, remove invalid chars, spaces -> dash
        String normalized = name.trim()
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");

        // Prepend timestamp for uniqueness
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        String slug = timestamp + "_" + normalized;

        // Limit total length
        if (slug.length() > 128) {
            slug = slug.substring(0, 128);
        }

        return slug + ext;
    }
}
