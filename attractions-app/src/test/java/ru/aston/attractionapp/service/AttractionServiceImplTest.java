package ru.aston.attractionapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.aston.attractionapp.AttractionsApplication;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.AttractionType;
import ru.aston.attractionapp.repository.AttractionRepository;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest(classes = AttractionsApplication.class)
@Testcontainers
class AttractionServiceImplTest {

    @Autowired
    AttractionRepository attractionRepository;

    @Autowired
    AttractionService attractionService = new AttractionServiceImpl(attractionRepository);

    @Test
    void testFindAllAttractionsSuccess() {
        assertEquals("Ufa", attractionService.findAllAttractions().get(0).getCity().getName());
        assertEquals("Beloreck", attractionService.findAllAttractions().get(1).getCity().getName());
        assertEquals("Starosubkhangulovo", attractionService.findAllAttractions().get(2).getCity().getName());
        assertEquals("Ufa", attractionService.findAllAttractions().get(3).getCity().getName());
        assertEquals(4, attractionService.findAllAttractions().size());
    }

    @Test
    void testAddAttractionSuccess() {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setName("Kremlin");
        attractionDto.setType(AttractionType.PALACE);
        attractionDto.setDescription("new description");
        attractionDto.setActivities(null);
        attractionDto.setCity(null);
        AttractionDto savedAttraction = attractionService.addAttraction(attractionDto);
        assertEquals(5, savedAttraction.getAttractionId());
        assertEquals("Kremlin", savedAttraction.getName());
        assertEquals("new description", savedAttraction.getDescription());
    }
}