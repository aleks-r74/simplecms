package stream.lexlab.simplecms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stream.lexlab.simplecms.exceptions.PageNotFound;
import stream.lexlab.simplecms.models.PageSummary;
import stream.lexlab.simplecms.repositories.PageRepository;
import stream.lexlab.simplecms.models.Page;
import stream.lexlab.simplecms.utils.Utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PageService {
    private final PageRepository pageRepository;
    private static final Pattern NON_LATIN = Pattern.compile("[^a-z0-9-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern MULTIPLE_HYPHENS = Pattern.compile("-{2,}");

    public Page loadPage(Long id){
        return pageRepository.findById(id)
                .orElseThrow(() -> new PageNotFound("Page with id %d not found".formatted(id)));
    }

    public Page loadPage(String slug){
        return pageRepository.findBySlug(slug).orElseThrow(() -> new IllegalArgumentException("Page with slug %s not found".formatted(slug)));
    }

    public List<PageSummary> getAll(){
        return pageRepository.findAllShort();
    }

    public Page savePage(Page p){
        p.setId(null);
        validate(p);
        if(p.getSlug() == null || p.getSlug().isBlank()) p.setSlug(Utils.toSlug(p.getTitle()));
        return pageRepository.save(p);
    }

    public Page update(Page p) {
        if(p.getId() == null || p.getId() == 0) throw new IllegalArgumentException("Provide page id to update the page");
        Page oldP = loadPage(p.getId());

        if (p.getParentId() != null) oldP.setParentId(p.getParentId());
        if (p.getTitle() != null) oldP.setTitle(p.getTitle());
        if (p.getContent() != null) oldP.setContent(p.getContent());
        if (p.getIsVisible() != null) oldP.setIsVisible(p.getIsVisible());
        if (p.getOrderIndex() != null) oldP.setOrderIndex(p.getOrderIndex());
        if (p.getMetaTitle() != null) oldP.setMetaTitle(p.getMetaTitle());
        if (p.getMetaDescription() != null) oldP.setMetaDescription(p.getMetaDescription());
        oldP.setUpdatedAt(LocalDateTime.now());

        return pageRepository.save(oldP);
    }

    public void deletePage(Long id){
        pageRepository.deleteById(id);
    }

    public void deletePage(String slug){
        pageRepository.deleteBySlug(slug);
    }

    private void validate(Page p){
        if(p.getTitle() == null || p.getContent() == null || p.getMetaTitle() == null || p.getMetaDescription() == null
                || p.getTitle().isBlank() || p.getContent().isBlank() || p.getMetaTitle().isBlank() || p.getMetaDescription().isBlank())
            throw new IllegalArgumentException("Page is not valid. Required: title, content, metaTitle, metaDescription");
    }


}
