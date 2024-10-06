package ru.aston.arrtactionapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.aston.attractionapp.AttractionsApplication;
import ru.aston.attractionapp.entity.AttractionType;
import ru.aston.attractionapp.repository.AttractionRepository;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test")
@SpringBootTest(classes = AttractionsApplication.class)
@Testcontainers
public class AttractionRepositoryTest {

    @Autowired
    AttractionRepository attractionRepository;
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:14.2")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }


    @ParameterizedTest
    @ValueSource(ints = {4})
    void testFindAllSuccess(int number) {
        assertEquals(number, attractionRepository.findAllByOrderByNameAsc().size());
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
    }

    @ParameterizedTest
    @ValueSource(longs = {4})
    void testFindAllByCityIdNotFound(Long number) {
        assertEquals(0, attractionRepository.findAllByCityCityId(number).size());
    }

}
