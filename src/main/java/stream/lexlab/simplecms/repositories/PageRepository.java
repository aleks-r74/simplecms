package stream.lexlab.simplecms.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import stream.lexlab.simplecms.models.Page;
import stream.lexlab.simplecms.models.PageSummary;

import java.util.List;
import java.util.Optional;

public interface PageRepository extends CrudRepository<Page, Long> {
    @Query("SELECT id, parent_id, title, slug, is_visible, order_index, meta_title, meta_description, created_at, updated_at FROM pages")
    List<PageSummary> findAllShort();

    Optional<Page> findBySlug(String slug);

    void deleteBySlug(String slug);
}

