package ru.aston.attractionapp.service;

import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.AttractionType;

import java.util.List;

public interface AttractionService {
    public List<AttractionDto> findAllAttractions();

    List<Attraction> findAttractionsByType(AttractionType type);

    List<Attraction> findAllByCityId(Long cityId);

    Attraction findAttractinById(Long attractionId);
}
