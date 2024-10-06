package ru.aston.attractionapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.AttractionType;

import java.util.List;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    List<Attraction> findAllByOrderByAttractionIdAsc();
    List<Attraction> findAllByType(AttractionType type);
    List<Attraction> findAllByCityCityId(Long cityId);

}
