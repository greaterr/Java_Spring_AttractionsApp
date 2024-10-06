package ru.aston.attractionapp.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.aston.attractionapp.AttractionsApplication;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.dto.CityDto;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.City;
import ru.aston.attractionapp.mapper.CityMapper;


import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = AttractionsApplication.class)
@Testcontainers
class CityRepositoryTest {

    @Autowired
    CityRepository cityRepository;

//    @Test
//    public void testSaveCitySuccess() {
//        CityDto cityDto = new CityDto();
//        cityDto.setCityId(4L);
//        cityDto.setName("Kazan");
//        cityDto.setPopulation(1200000);
//        City city = CityMapper.INSTANSE.toCityEntity(cityDto);
//        City newCity = cityRepository.save(city);
//        cityDto = CityMapper.INSTANSE.toCityDto(newCity);
//        assertEquals(4, cityDto.getCityId());
//        assertEquals("Kazan", cityDto.getName());
//    }

}