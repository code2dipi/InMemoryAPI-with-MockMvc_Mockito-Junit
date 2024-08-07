package com.dipi.inmemory_api_mock_mvc_test.service;

import com.dipi.inmemory_api_mock_mvc_test.payload.Presentation;
import com.dipi.inmemory_api_mock_mvc_test.payload.Slide;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InMemoryDataStoreImpl implements InMemoryDataStore  {
    private final Map<String, Presentation> presentationsMap;
    public InMemoryDataStoreImpl(){
        presentationsMap=new HashMap<>();
    }

    @Override
    public void addPresentation(Presentation presentation) {
    presentationsMap.put(presentation.getId(), presentation);
    }

    @Override
    public Collection<Presentation> getPresentations() {
        return presentationsMap.values();
    }
    @Override
    public Presentation getPresentation(String id) {
        return presentationsMap.get(id);
    }



    @Override
    public void updatePresentation(Presentation presentation) {
        presentationsMap.put(presentation.getId(), presentation);

    }

    @Override
    public void deletePresentation(String id) {
        presentationsMap.remove(id);

    }

    @Override
    public void addSlide(String presentationId, Slide newSlide) {
     Presentation presentation=presentationsMap.get(presentationId);
     if(presentation!=null){
         presentation.getSlides().add(newSlide);
     }
    }

    @Override
    public Slide getSlide(String presentationId, String slideId) {
        Presentation presentation = presentationsMap.get(presentationId);
        if (presentation != null) {
            Optional<Slide> slide = presentation
                    .getSlides()
                    .stream()
                    .filter(s -> s.getId().equals(slideId))
                    .findFirst();
            return slide.orElse(null);
        }


        return null;
    }

    @Override
    public List<Slide> getSlides(String presentationId) {
        Presentation presentation= presentationsMap.get(presentationId);

        return presentation!=null ? presentation.getSlides():new ArrayList<>();
    }

    @Override
    public void updateSlide(String presentationId, Slide updatedSlide) {
        Presentation presentation=presentationsMap.get(presentationId);
        if(presentation!=null){
           presentation.getSlides()
                           .removeIf(slide1->slide1.getId().equals(updatedSlide.getId()));
            presentation.getSlides().add(updatedSlide);
        }


    }

    @Override
    public void deleteSlide(String presentationId, String slideId) {
        Presentation presentation=presentationsMap.get(presentationId);
        if(presentation!=null){
            presentation.getSlides().removeIf(slide1->slide1.getId().equals(slideId));
        }
    }
}
