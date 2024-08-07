package com.dipi.inmemory_api_mock_mvc_test.service;

import com.dipi.inmemory_api_mock_mvc_test.payload.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InMemoryDataStoreImplTest {
    @Mock
    private InMemoryDataStore dataStore;

    @InjectMocks
    private InMemoryDataStoreImpl dataStoreImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePresentation() throws Exception {
        // Given
        Presentation presentation = new Presentation();
        presentation.setId("1");
        presentation.setTitle("Presentation 1");

        doNothing().when(dataStore).addPresentation(presentation);

        // When
        dataStoreImpl.addPresentation(presentation);

        // Then
        verify(dataStore, times(1)).addPresentation(presentation);

    }

    @Test
    public void testGetPresentation() throws Exception {
        // Given
        Presentation presentation = new Presentation();
        presentation.setId("1");
        presentation.setTitle("Test Presentation");

        // Mock the behavior of dataStore
        when(dataStore.getPresentation("1")).thenReturn(presentation);

        Presentation result = dataStoreImpl.getPresentation("1");

        //Assert
       // assertNotNull(result);
        assertEquals("Test Presentation", result.getTitle());
        verify(dataStore, times(1)).getPresentation("1");

     // TODO: Remaining tests........

    }
}