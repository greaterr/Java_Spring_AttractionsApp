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
import static org.mockito.Mockito.when;
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
    void updateAttractionTest() throws Exception {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setAttractionId(1L);
        attractionDto.setDescription("Updated description");

        when(attractionService.updateAttraction(any(AttractionDto.class))).thenReturn(attractionDto);

        mockMvc.perform(post("/attractions/update")
                        .param("attractionId", "1")
                        .param("description", "Updated description"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated description"));
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
    void FindAllAttractionsTest() throws Exception {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setName("Test Attraction");

        when(attractionService.findAllAttractions()).thenReturn(List.of(attractionDto));

        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Attraction"));
    }

    @Test
    void FindAttractionByIdTest() throws Exception {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setAttractionId(1L);
        attractionDto.setName("Test Attraction");

        when(attractionService.findAttractionById(1L)).thenReturn(attractionDto);

        mockMvc.perform(get("/attractions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Attraction"));
    }

    @Test
    void FindAttractionsByTypeTest() throws Exception {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setName("Type-based Attraction");

        when(attractionService.findAttractionsByType(AttractionType.NATURE_PARK)).thenReturn(List.of(attractionDto));

        mockMvc.perform(get("/attractions/type/NATURE_PARK"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Type-based Attraction"));
    }
}
