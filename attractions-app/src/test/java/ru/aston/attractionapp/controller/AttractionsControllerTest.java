package ru.aston.attractionapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.AttractionType;
import ru.aston.attractionapp.service.AttractionService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AttractionsController.class)
class AttractionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AttractionService attractionService;

    @Test
    void addAttractionTest() throws Exception {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setName("New Attraction");

        when(attractionService.addAttraction(any(AttractionDto.class))).thenReturn(attractionDto);

        mockMvc.perform(post("/attractions/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Attraction\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Attraction"));
    }

    @Test
    void addAttractionTest_withException() throws Exception {
        when(attractionService.addAttraction(any(AttractionDto.class))).thenThrow(new IllegalArgumentException("Attraction already exists"));

        mockMvc.perform(post("/attractions/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Attraction\"}"))
                .andExpect(status().isConflict())
                .andExpect(content().string("Attraction already exists"));
    }

    @Test
    void updateAttractionTest() throws Exception {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setAttractionId(1L);
        attractionDto.setDescription("Updated description");

        when(attractionService.updateAttraction(any(AttractionDto.class))).thenReturn(attractionDto);

        mockMvc.perform(put("/attractions/update")
                        .param("attractionId", "1")
                        .param("description", "Updated description"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated description"));
    }
    @Test
    void updateAttractionTest_withException() throws Exception {
        when(attractionService.updateAttraction(any(AttractionDto.class))).thenThrow(new IllegalArgumentException("Invalid ID"));

        mockMvc.perform(put("/attractions/update")
                        .param("attractionId", "1")
                        .param("description", "Updated description"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Invalid ID"));
    }

    @Test
    void deleteAttractionTest() throws Exception {
        doNothing().when(attractionService).deleteAttractionById(1L);

        mockMvc.perform(delete("/attractions/delete")
                        .param("attractionId", "1"))
                .andExpect(status().isNoContent());

        doThrow(new IllegalArgumentException("There is no attraction with id 1 in database"))
                .when(attractionService).deleteAttractionById(1L);

        mockMvc.perform(delete("/attractions/delete")
                        .param("attractionId", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findAllAttractionsFilteredTest() throws Exception {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setName("Filtered Attraction");

        when(attractionService.findAllAttractionsFiltered(any(), any())).thenReturn(List.of(attractionDto));

        mockMvc.perform(get("/attractions/filter")
                        .param("orderByName", "asc")
                        .param("attractionType", "NATURE_PARK"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Filtered Attraction"));
    }
    @Test
    void findAllAttractionsFilteredTest_withException() throws Exception {
        when(attractionService.findAllAttractionsFiltered(any(), any())).thenThrow(new IllegalArgumentException("Filter error"));

        mockMvc.perform(get("/attractions/filter")
                        .param("orderByName", "asc")
                        .param("attractionType", "NATURE_PARK"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Filter error"));
    }


    @Test
    void FindAttractionsByCityTest() throws Exception {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setName("City Attraction");

        when(attractionService.findAllByCityName(any())).thenReturn(List.of(attractionDto));

        mockMvc.perform(get("/attractions/city")
                        .param("cityName", "Some City"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("City Attraction"));
    }
    @Test
    void findAttractionsByCityTest_withException() throws Exception {
        when(attractionService.findAllByCityName(any())).thenThrow(new IllegalArgumentException("City not found"));

        mockMvc.perform(get("/attractions/city")
                        .param("cityName", "Some City"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("City not found"));
    }


    @Test
    void FindAllAttractionsTest() throws Exception {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setName("Test Attraction");

        when(attractionService.findAllAttractions()).thenReturn(List.of(attractionDto));

        mockMvc.perform(get("/attractions/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Attraction"));
    }
}
