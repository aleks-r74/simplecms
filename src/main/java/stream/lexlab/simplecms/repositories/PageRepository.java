package stream.lexlab.simplecms.repositories;

import org.springframework.data.repository.CrudRepository;
import stream.lexlab.simplecms.models.Page;
import java.util.Optional;

public interface PageRepository extends CrudRepository<Page, Long> {
    Optional<Page> findBySlug(String slug);
    void deleteBySlug(String slug);
}

