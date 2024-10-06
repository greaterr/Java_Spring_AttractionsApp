package ru.aston.attractionapp.service;

import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.AttractionType;

import java.util.List;

public interface AttractionService {
    public List<AttractionDto> findAllAttractions();

    List<AttractionDto> findAttractionsByType(AttractionType type);

    List<AttractionDto> findAllByCityId(Long cityId);

    AttractionDto findAttractinById(Long attractionId);
}
