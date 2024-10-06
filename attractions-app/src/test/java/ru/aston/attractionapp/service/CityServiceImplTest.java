package ru.aston.attractionapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.aston.attractionapp.dto.CityDto;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
class CityServiceImplTest {

    @Autowired
    CityService cityService;
    @Test
    void testAddCitySuccess() {
        CityDto cityDto = new CityDto();
        cityDto.setName("Kazan");
        cityDto.setPopulation(1200000);
        cityDto.setAttractions(null);
        cityDto.setHasMetro(true);
        CityDto savedCity = cityService.addCity(cityDto);
        assertEquals("Kazan", savedCity.getName());
    }
}