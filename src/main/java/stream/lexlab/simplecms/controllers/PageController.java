package stream.lexlab.simplecms.controllers;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stream.lexlab.simplecms.models.Page;
import stream.lexlab.simplecms.models.PageSummary;
import stream.lexlab.simplecms.service.PageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pages")
public class PageController {
    @PostConstruct
    public void start(){
        System.out.println("Controller started");
    }

    private final PageService pageService;

    @GetMapping("/all")
    public ResponseEntity<List<PageSummary>> getShorts(){
        return ResponseEntity.ok(pageService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Page> loadPageById(@PathVariable Long id) {
        return ResponseEntity.ok(pageService.loadPage(id));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<Page> loadPageBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(pageService.loadPage(slug));
    }

    @PostMapping
    public ResponseEntity<Page> savePage(@RequestBody Page p) {
        Page saved = pageService.savePage(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping
    public ResponseEntity<Page> updatePage(@RequestBody Page p) {
        Page updated = pageService.update(p);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletePage(@PathVariable Long id) {
        pageService.deletePage(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/slug/{slug}")
    public ResponseEntity<Void> deletePage(@PathVariable String slug) {
        pageService.deletePage(slug);
        return ResponseEntity.noContent().build();
    }
}
