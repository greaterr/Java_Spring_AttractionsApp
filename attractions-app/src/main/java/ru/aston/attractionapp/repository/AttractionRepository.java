package ru.aston.attractionapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.AttractionType;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    Optional<Attraction> findByNameAndCityCityId(String cityName, Long cityId);
    Optional<Attraction> findByNameAndCityIsNull(String cityName);
    List<Attraction> findAllByOrderByAttractionIdAsc();
    List<Attraction> findAllByOrderByNameAsc();
    List<Attraction> findAllByType(AttractionType type);
    List<Attraction> findAllByCityCityId(Long cityId);
    List<Attraction> findAllByCityName(String cityName);

}
