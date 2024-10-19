package ru.aston.attractionapp.mapper;

import org.junit.jupiter.api.Test;
import ru.aston.attractionapp.dto.CityDto;
import ru.aston.attractionapp.entity.City;
import ru.aston.attractionapp.entity.Attraction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityMapperTest {

    CityMapper mapper = CityMapper.INSTANSE;

    @Test
    void testToCityDto() {
        City city = new City();
        city.setCityId(1L);
        city.setName("Test City");

        Attraction attraction = new Attraction();
        attraction.setAttractionId(1L);
        attraction.setName("Test Attraction");

        city.setAttractions(List.of(attraction));

        CityDto cityDto = mapper.toCityDto(city);

        assertEquals(city.getCityId(), cityDto.getCityId());
        assertEquals(city.getName(), cityDto.getName());

        assertNull(cityDto.getAttractions());
    }

    @Test
    void testToCityEntity() {
        CityDto cityDto = new CityDto();
        cityDto.setCityId(1L);
        cityDto.setName("Test City");

        City city = mapper.toCityEntity(cityDto);

        assertEquals(cityDto.getCityId(), city.getCityId());
        assertEquals(cityDto.getName(), city.getName());

        assertNull(city.getAttractions());
    }
}
