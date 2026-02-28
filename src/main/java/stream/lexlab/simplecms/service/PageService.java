package stream.lexlab.simplecms.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stream.lexlab.simplecms.exceptions.PageNotFound;
import stream.lexlab.simplecms.repositories.PageRepository;
import stream.lexlab.simplecms.models.Page;

@Service
@RequiredArgsConstructor
public class PageService {
    private final PageRepository pageRepository;

    public Page loadPage(Long id){
        return pageRepository.findById(id)
                .orElseThrow(() -> new PageNotFound("Page with id %d not found".formatted(id)));
    }

    public Page loadPage(String slug){
        return pageRepository.findBySlug(slug).orElseThrow(() -> new IllegalArgumentException("Page with slug %s not found".formatted(slug)));
    }

    public Page savePage(Page p){
        p.setId(null);
        return pageRepository.save(p);
    }

    public Page update(Page p) {
        if(p.getId() == null || p.getId() == 0) throw new IllegalArgumentException("Provide page id to update the page");
        Page oldP = loadPage(p.getId());

        if (p.getTitle() != null) oldP.setTitle(p.getTitle());
        if (p.getSlug() != null) oldP.setSlug(p.getSlug());
        if (p.getContent() != null) oldP.setContent(p.getContent());
        if (p.getIsVisible() != null) oldP.setIsVisible(p.getIsVisible());
        if (p.getOrderIndex() != null) oldP.setOrderIndex(p.getOrderIndex());
        if (p.getMetaTitle() != null) oldP.setMetaTitle(p.getMetaTitle());
        if (p.getMetaDescription() != null) oldP.setMetaDescription(p.getMetaDescription());

        return pageRepository.save(oldP);
    }

    public void deletePage(Long id){
        pageRepository.deleteById(id);
    }

    public void deletePage(String slug){
        pageRepository.deleteBySlug(slug);
    }
    @PostConstruct
    void test(){
        System.out.println("Page service started");
    }
}
