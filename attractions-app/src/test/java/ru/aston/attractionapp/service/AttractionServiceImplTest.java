package ru.aston.attractionapp.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.aston.attractionapp.AttractionsApplication;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.AttractionType;
import ru.aston.attractionapp.repository.AttractionRepository;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest(classes = AttractionsApplication.class)
@Testcontainers
@Transactional
class AttractionServiceImplTest {

    @Autowired
    AttractionRepository attractionRepository;

    @Autowired
    AttractionService attractionService = new AttractionServiceImpl(attractionRepository);

    @Test
    @Order(1)
    void testFindAllAttractionsSuccess() {
        assertEquals("Ufa", attractionService.findAllAttractions().get(0).getCity().getName());
        assertEquals("Beloreck", attractionService.findAllAttractions().get(1).getCity().getName());
        assertEquals("Starosubkhangulovo", attractionService.findAllAttractions().get(2).getCity().getName());
        assertEquals("Ufa", attractionService.findAllAttractions().get(3).getCity().getName());
        assertEquals(4, attractionService.findAllAttractions().size());
    }

    @Test
    @Rollback
    @Order(2)
    void testAddAttractionSuccess() {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setName("Kremlin");
        attractionDto.setType(AttractionType.PALACE);
        attractionDto.setDescription("new description");
        attractionDto.setActivities(null);
        attractionDto.setCity(null);
        AttractionDto savedAttraction = attractionService.addAttraction(attractionDto);
        assertEquals(6, savedAttraction.getAttractionId());
        assertEquals("Kremlin", savedAttraction.getName());
        assertEquals("new description", savedAttraction.getDescription());
    }

    @Test
    @Rollback
    @Order(3)
    void testAddAttractionFailed() {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setName("Kremlin");
        attractionDto.setType(AttractionType.PALACE);
        attractionDto.setDescription("new description");
        attractionDto.setActivities(null);
        attractionDto.setCity(null);
        attractionService.addAttraction(attractionDto);
        AttractionDto newAttractionDto = new AttractionDto();
        newAttractionDto.setName("Kremlin");
        newAttractionDto.setType(AttractionType.PALACE);
        newAttractionDto.setDescription("new description");
        newAttractionDto.setActivities(null);
        newAttractionDto.setCity(null);
        assertThrows(IllegalArgumentException.class, () -> attractionService.addAttraction(newAttractionDto));
    }
}