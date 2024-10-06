package ru.aston.attractionapp.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.aston.attractionapp.AttractionsApplication;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.Attraction;


import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = AttractionsApplication.class)
@Testcontainers
class AttractionMapperTest {

    AttractionMapper mapper = AttractionMapper.INSTANCE;


    @Test
    public void testToAttractionDto() {

        Attraction attraction = new Attraction();
        attraction.setAttractionId(1L);
        attraction.setName("city");
        attraction.setActivities(null);

        AttractionDto actualDto = mapper.toAttractionDto(attraction);

        assertEquals(actualDto.getAttractionId(), attraction.getAttractionId());
        assertEquals(actualDto.getName(), attraction.getName());
        assertNull(actualDto.getActivities());
    }

    @Test
    public void testToAttractionEntity() {
        AttractionDto dto = new AttractionDto();
        dto.setAttractionId(1L);
        dto.setCity(null);
        dto.setName("city");
        dto.setDescription("city description");
        dto.setActivities(null);

        Attraction actualEntity = mapper.toAttractionEntity(dto);

        assertEquals(actualEntity.getAttractionId(), dto.getAttractionId());
        assertEquals(actualEntity.getDescription(), dto.getDescription());
        assertNull(actualEntity.getActivities());
    }
}
