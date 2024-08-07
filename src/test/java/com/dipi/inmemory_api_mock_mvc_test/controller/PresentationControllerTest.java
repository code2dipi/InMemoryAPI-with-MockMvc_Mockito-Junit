package com.dipi.inmemory_api_mock_mvc_test.controller;

import com.dipi.inmemory_api_mock_mvc_test.payload.Presentation;
import com.dipi.inmemory_api_mock_mvc_test.payload.Slide;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class PresentationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    // Test for creating and retrieving a presentation
    @Test
    public void testCreateAndGetPresentation() throws Exception {
        Slide slide1 = new Slide();
        slide1.setId("1");
        slide1.setContent("Slide 1 Content");

        Slide slide2 = new Slide();
        slide2.setId("2");
        slide2.setContent("Slide 2 Content");

        Presentation presentation = new Presentation();
        presentation.setId("1");
        presentation.setTitle("My presentation");
        presentation.setSlides(Arrays.asList(slide1, slide2));

        // Create the presentation
        mockMvc.perform(post("/api/presentations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(presentation)))
                .andExpect(status().isOk());

        // Get the created presentation
        mockMvc.perform(get("/api/presentations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("My presentation"))
                .andExpect(jsonPath("$.slides").isArray())
                .andExpect(jsonPath("$.slides[0].content").value("Slide 1 Content"))
                .andExpect(jsonPath("$.slides[1].content").value("Slide 2 Content"));
    }

    // Test for retrieving all presentations
    @Test
    public void testGetAllPresentations() throws Exception {
        Slide slide1 = new Slide();
        slide1.setId("3");
        slide1.setContent("Slide 3 Content");

        Slide slide2 = new Slide();
        slide2.setId("4");
        slide2.setContent("Slide 4 Content");

        Presentation presentation1 = new Presentation();
        presentation1.setId("1");
        presentation1.setTitle("My Presentation 1");
        presentation1.setSlides(Arrays.asList(slide1, slide2));

        Slide slide3 = new Slide();
        slide3.setId("5");
        slide3.setContent("Slide 5 Content");

        Slide slide4 = new Slide();
        slide4.setId("6");
        slide4.setContent("Slide 6 Content");

        Presentation presentation2 = new Presentation();
        presentation2.setId("2");
        presentation2.setTitle("My Presentation 2");
        presentation2.setSlides(Arrays.asList(slide3, slide4));

        // Create the presentations
        mockMvc.perform(post("/api/presentations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(presentation1)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/presentations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(presentation2)))
                .andExpect(status().isOk());

        // Get all presentations
        mockMvc.perform(get("/api/presentations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").value("My Presentation 1"))
                .andExpect(jsonPath("$[1].title").value("My Presentation 2"));
    }

    // Test for updating a presentation
    @Test
    public void testUpdatePresentation() throws Exception {
        Slide slide1 = new Slide();
        slide1.setId("1");
        slide1.setContent("Slide 1 Content");

        Slide slide2 = new Slide();
        slide2.setId("2");
        slide2.setContent("Slide 2 Content");

        Presentation presentation = new Presentation();
        presentation.setId("1");
        presentation.setTitle("Existing title");
        presentation.setSlides(Arrays.asList(slide1, slide2));

        // Create the presentation
        mockMvc.perform(post("/api/presentations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(presentation)))
                .andExpect(status().isOk());

        // Update the presentation
        presentation.setTitle("Updated Title");

        mockMvc.perform(put("/api/presentations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(presentation)))
                .andExpect(status().isOk());

        // Get the updated presentation
        mockMvc.perform(get("/api/presentations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    // Test for deleting a presentation
    @Test
    public void testDeletePresentation() throws Exception {
        Slide slide1 = new Slide();
        slide1.setId("1");
        slide1.setContent("Slide 1 Content");

        Slide slide2 = new Slide();
        slide2.setId("2");
        slide2.setContent("Slide 2 Content");

        Presentation presentation = new Presentation();
        presentation.setId("1");
        presentation.setTitle("Presentation to be deleted");
        presentation.setSlides(Arrays.asList(slide1, slide2));

        // Create the presentation
        mockMvc.perform(post("/api/presentations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(presentation)))
                .andExpect(status().isOk());

        // Delete the presentation
        mockMvc.perform(delete("/api/presentations/1"))
                .andExpect(status().isOk());

        // Verify the presentation is deleted
        mockMvc.perform(get("/api/presentations/1"))
                .andExpect(status().isNotFound());
    }

    // Test for creating and retrieving slides
    @Test
    public void testCreateAndGetSlides() throws Exception {
        // Step 1: Create a presentation without slides
        Presentation presentation = new Presentation();
        presentation.setId("4");
        presentation.setTitle("Presentation with Slides");

        mockMvc.perform(post("/api/presentations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(presentation)))
                .andExpect(status().isOk());

        // Step 2: Add slides to the presentation
        Slide slide1 = new Slide();
        slide1.setId("1");
        slide1.setContent("Slide 1 Content");

        Slide slide2 = new Slide();
        slide2.setId("2");
        slide2.setContent("Slide 2 Content");

        mockMvc.perform(post("/api/presentations/4/slides")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(slide1)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/presentations/4/slides")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(slide2)))
                .andExpect(status().isOk());

        // Step 3: Retrieve the slides and verify their content
        MvcResult returnResponse = mockMvc.perform(get("/api/presentations/4/slides"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].content").value("Slide 1 Content"))
                .andExpect(jsonPath("$[1].content").value("Slide 2 Content"))
                .andReturn();

        // Print the response content to the console
        String jsonResponse = returnResponse.getResponse().getContentAsString();
        System.out.println(jsonResponse);
    }

    // Test for updating a slide
    @Test
    public void testUpdateSlide() throws Exception {
        // Step 1: Create a presentation without slides
        Presentation presentation = new Presentation();
        presentation.setId("5");
        presentation.setTitle("Presentation to Update Slide");

        mockMvc.perform(post("/api/presentations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(presentation)))
                .andExpect(status().isOk());

        // Step 2: Add a slide to the presentation
        Slide slide = new Slide();
        slide.setId("1");
        slide.setContent("Original Slide Content");

        mockMvc.perform(post("/api/presentations/5/slides")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(slide)))
                .andExpect(status().isOk());

        // Step 3: Update the slide
        slide.setContent("Updated Slide Content");

        mockMvc.perform(put("/api/presentations/5/slides/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(slide)))
                .andExpect(status().isOk());

        // Step 4: Verify the slide is updated
        MvcResult mvcResult = mockMvc.perform(get("/api/presentations/5/slides/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Updated Slide Content"))
                .andReturn();

        // Print the response content to the console
        String jsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println("Updated Slide: " + jsonResponse);
    }

    // Test for deleting a slide
    @Test
    public void testDeleteSlide() throws Exception {
        // Step 1: Create a presentation without slides
        Presentation presentation = new Presentation();
        presentation.setId("6");
        presentation.setTitle("Presentation to Delete Slide");

        mockMvc.perform(post("/api/presentations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(presentation)))
                .andExpect(status().isOk());

        // Step 2: Add a slide to the presentation
        Slide slide = new Slide();
        slide.setId("1");
        slide.setContent("Slide Content to Delete");

        mockMvc.perform(post("/api/presentations/6/slides")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(slide)))
                .andExpect(status().isOk());

        // Step 3: Delete the slide
        mockMvc.perform(delete("/api/presentations/6/slides/1"))
                .andExpect(status().isOk());

        // Step 4: Verify the slide is deleted
        mockMvc.perform(get("/api/presentations/6/slides/1"))
                .andExpect(status().isNotFound());
    }

    // Test for retrieving a single slide
    @Test
    public void testGetSingleSlide() throws Exception {
        // Step 1: Create a presentation without slides
        Presentation presentation = new Presentation();
        presentation.setId("7");
        presentation.setTitle("Presentation to Get Single Slide");

        mockMvc.perform(post("/api/presentations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(presentation)))
                .andExpect(status().isOk());

        // Step 2: Add a slide to the presentation
        Slide slide = new Slide();
        slide.setId("1");
        slide.setContent("Single Slide Content");

        mockMvc.perform(post("/api/presentations/7/slides")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(slide)))
                .andExpect(status().isOk());

        // Step 3: Retrieve the slide and verify its content
        MvcResult mvcResult = mockMvc.perform(get("/api/presentations/7/slides/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Single Slide Content"))
                .andReturn();

        // Print the response content to the console
        String jsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println("Retrieved Single Slide: " + jsonResponse);
    }
}
