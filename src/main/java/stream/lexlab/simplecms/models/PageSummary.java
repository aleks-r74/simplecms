package stream.lexlab.simplecms.models;

import java.time.LocalDateTime;

public record PageSummary(Long id,
                          Long parentId,
                          String title,
                          String slug,
                          Integer isVisible,
                          Integer orderIndex,
                          String metaTitle,
                          String metaDescription,
                          LocalDateTime createdAt,
                          LocalDateTime updatedAt) {}
