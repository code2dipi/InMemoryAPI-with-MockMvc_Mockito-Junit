package com.dipi.inmemory_api_mock_mvc_test.service;

import com.dipi.inmemory_api_mock_mvc_test.payload.Presentation;
import com.dipi.inmemory_api_mock_mvc_test.payload.Slide;

import java.util.Collection;
import java.util.List;

public interface InMemoryDataStore {
     void addPresentation(Presentation presentation);
     Presentation getPresentation(String id);

     Collection<Presentation> getPresentations();

     void  updatePresentation(Presentation presentation);

     void deletePresentation(String id);

    void addSlide(String presentationId, Slide slide);
    Slide getSlide(String presentationId,String slideId);

    List<Slide> getSlides(String presentationId);

    void updateSlide(String presentationId, Slide slide);
    void deleteSlide(String presentationId,String slideId);



}
