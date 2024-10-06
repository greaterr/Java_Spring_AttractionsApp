package ru.aston.arrtactionapp.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.aston.attractionapp.AttractionsApplication;
import ru.aston.attractionapp.entity.AttractionType;
import ru.aston.attractionapp.repository.AttractionRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test")
@SpringBootTest(classes = AttractionsApplication.class)
@Testcontainers
class AttractionRepositoryTest {

    @Autowired
    AttractionRepository attractionRepository;

    @ParameterizedTest
    @ValueSource(ints = {4})
    void testFindAllSuccess(int number) {
        assertEquals(number, attractionRepository.findAllByOrderByAttractionIdAsc().size());
    }

    @Test
    void testFindAllByTypeSuccess() {
        assertEquals(1, attractionRepository.findAllByType(AttractionType.MEMORIAL).size());
        assertEquals(0, attractionRepository.findAllByType(AttractionType.PALACE).size());
    }

    @ParameterizedTest
    @ValueSource(longs = {1})
    void testFindAllByCityIdSuccess(Long number) {
        assertEquals(2, attractionRepository.findAllByCityCityId(number).size());
        assertEquals(number, attractionRepository.findAllByCityCityId(number).get(0).getCity().getCityId());

    }

    @ParameterizedTest
    @ValueSource(longs = {4})
    void testFindAllByCityIdNotFound(Long number) {
        assertEquals(0, attractionRepository.findAllByCityCityId(number).size());
    }

}
