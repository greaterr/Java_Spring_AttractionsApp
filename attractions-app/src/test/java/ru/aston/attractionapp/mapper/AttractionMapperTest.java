package ru.aston.attractionapp.mapper;

import org.junit.jupiter.api.Test;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.dto.CityDto;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.City;
import ru.aston.attractionapp.entity.Activity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AttractionMapperTest {

    AttractionMapper mapper = AttractionMapper.INSTANCE;

    @Test
    void testToAttractionDto() {
        City city = new City();
        city.setCityId(1L);
        city.setName("CityName");

        Activity activity = new Activity();
        activity.setActivityId(1L);
        activity.setName("ActivityName");

        Attraction attraction = new Attraction();
        attraction.setAttractionId(1L);
        attraction.setName("AttractionName");
        attraction.setCity(city);
        attraction.setActivities(List.of(activity));

        AttractionDto actualDto = mapper.toAttractionDto(attraction);

        assertEquals(attraction.getAttractionId(), actualDto.getAttractionId());
        assertEquals(attraction.getName(), actualDto.getName());

        assertNotNull(actualDto.getCity());
        assertEquals(attraction.getCity().getCityId(), actualDto.getCity().getCityId());
        assertEquals(attraction.getCity().getName(), actualDto.getCity().getName());

        assertNotNull(actualDto.getActivities());
        assertEquals(1, actualDto.getActivities().size());
        assertEquals(attraction.getActivities().get(0).getActivityId(), actualDto.getActivities().get(0).getActivityId());
    }

    @Test
    void testToAttractionEntity() {
        CityDto cityDto = new CityDto();
        cityDto.setCityId(1L);
        cityDto.setName("CityName");

        AttractionDto dto = new AttractionDto();
        dto.setAttractionId(1L);
        dto.setName("AttractionName");
        dto.setCity(cityDto);
        dto.setActivities(null);

        Attraction actualEntity = mapper.toAttractionEntity(dto);

        assertEquals(dto.getAttractionId(), actualEntity.getAttractionId());
        assertEquals(dto.getName(), actualEntity.getName());

        assertNotNull(actualEntity.getCity());
        assertEquals(dto.getCity().getCityId(), actualEntity.getCity().getCityId());
        assertEquals(dto.getCity().getName(), actualEntity.getCity().getName());

        assertNull(actualEntity.getActivities());
        assertEquals(1, actualEntity.getAttractionId());
        assertNull(dto.getActivities());
    }
}
