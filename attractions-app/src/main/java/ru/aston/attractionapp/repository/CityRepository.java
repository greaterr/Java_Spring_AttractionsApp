package ru.aston.attractionapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.attractionapp.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
