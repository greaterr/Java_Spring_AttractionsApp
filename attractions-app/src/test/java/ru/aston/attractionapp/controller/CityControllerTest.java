package ru.aston.attractionapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.aston.attractionapp.dto.CityDto;
import ru.aston.attractionapp.service.CityService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CityController.class)
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @Test
    void addCitySuccessTest() throws Exception {
        CityDto cityDto = new CityDto();
        cityDto.setName("New city");
        when(cityService.addCity(any(CityDto.class))).thenReturn(cityDto);

        mockMvc.perform(post("/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New city\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New city"));

    }
    @Test
    void addCityFailTest() throws Exception {
        CityDto cityDto = new CityDto();
        cityDto.setName("New city");
        cityDto.setHasMetro(false);

        when(cityService.addCity(any(CityDto.class))).thenReturn(cityDto);
        mockMvc.perform(post("/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New city\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New city"));
        when(cityService.addCity(any(CityDto.class)))
                .thenThrow(new IllegalArgumentException("City already exists"));

        mockMvc.perform(post("/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New city\"}"))
                .andExpect(status().isConflict());
                    }
}