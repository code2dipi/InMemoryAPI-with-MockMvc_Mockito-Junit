package com.dipi.inmemory_api_mock_mvc_test.controller;

import com.dipi.inmemory_api_mock_mvc_test.payload.Presentation;
import com.dipi.inmemory_api_mock_mvc_test.payload.Slide;
import com.dipi.inmemory_api_mock_mvc_test.service.InMemoryDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/presentations")
public class PresentationController {
    private final InMemoryDataStore inMemoryDataStore;

    @Autowired
    public PresentationController(InMemoryDataStore inMemoryDataStore) {
        this.inMemoryDataStore = inMemoryDataStore;
    }

    @PostMapping
    public ResponseEntity<Void> addPresentation(@RequestBody Presentation presentation) {
        inMemoryDataStore.addPresentation(presentation);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Presentation>> getAllPresentations() {
        return ResponseEntity.ok(inMemoryDataStore.getPresentations());
    }

    @GetMapping("/{presentationId}")
    public ResponseEntity<Presentation> getPresentation(@PathVariable String presentationId) {
        Presentation presentation = inMemoryDataStore.getPresentation(presentationId);
        return presentation != null ? ResponseEntity.ok(presentation) : ResponseEntity
                .notFound()
                .build();
    }

    @PutMapping("/{presentationId}")
    public ResponseEntity<Void> updatePresentation(@PathVariable String presentationId, @RequestBody Presentation presentation) {
        presentation.setId(presentationId);
        inMemoryDataStore.updatePresentation(presentation);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePresentation(@PathVariable String id) {
        inMemoryDataStore.deletePresentation(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{presentationId}/slides")
    public ResponseEntity<Void> addSlide(@PathVariable String presentationId, @RequestBody Slide slide) {
        inMemoryDataStore.addSlide(presentationId, slide);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{presentationId}/slides")
    public ResponseEntity<List<Slide>> getAllSlides(@PathVariable String presentationId) {
        var slides = inMemoryDataStore.getSlides(presentationId);
        return ResponseEntity.ok(slides);
    }


    @GetMapping("/{presentationId}/slides/{slideId}")
    public ResponseEntity<Slide> getSlide(@PathVariable String presentationId, @PathVariable String slideId) {
        Slide slide = inMemoryDataStore.getSlide(presentationId, slideId);
        return slide != null ? ResponseEntity.ok(slide) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{presentationId}/slides/{slideId}")
    public ResponseEntity<Void> updateSlide(@PathVariable String presentationId, @PathVariable String slideId, @RequestBody Slide slide) {
        slide.setId(slideId);
        inMemoryDataStore.updateSlide(presentationId, slide);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{presentationId}/slides/{slideId}")
    public ResponseEntity<Void> deleteSlide(@PathVariable String presentationId, @PathVariable String slideId) {
        inMemoryDataStore.deleteSlide(presentationId, slideId);
        return ResponseEntity.ok().build();
    }
}



