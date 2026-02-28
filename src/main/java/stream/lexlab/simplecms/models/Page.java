package stream.lexlab.simplecms.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("pages")
public class Page {
    @Id
    private Long id;
    private Long parentId = 0L;
    private String title;
    private String slug;
    private String content;
    private Integer isVisible = 1;
    private Integer orderIndex = 0;
    private String metaTitle;
    private String metaDescription;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}