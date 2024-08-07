package com.dipi.inmemory_api_mock_mvc_test.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Presentation {
    private String id;
    private String title;
    private List<Slide> slides= new ArrayList<>();

}
