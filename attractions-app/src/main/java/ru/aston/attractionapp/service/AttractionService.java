package ru.aston.attractionapp.service;

import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.AttractionType;

import java.util.List;

public interface AttractionService {
    AttractionDto addAttraction(AttractionDto attractionDto) throws IllegalArgumentException;

    List<AttractionDto> findAllAttractions();

    List<AttractionDto> findAttractionsByType(AttractionType type);

    List<AttractionDto> findAllByCityId(Long cityId);

    List<AttractionDto> findAllByCityName(String cityName) throws IllegalArgumentException;

    AttractionDto findAttractionById(Long attractionId);

    List<AttractionDto> findAllAttractionsFiltered(String orderByName, String filterByType) throws IllegalArgumentException;

    AttractionDto updateAttraction(AttractionDto updatedAttraction) throws IllegalArgumentException;

    void deleteAttractionById(long l) throws IllegalArgumentException;
}
