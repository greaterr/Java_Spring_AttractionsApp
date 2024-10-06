package ru.aston.attractionapp.service;

import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.AttractionType;

import java.util.List;

public interface AttractionService {
    AttractionDto addAttraction(AttractionDto attractionDto);

    public List<AttractionDto> findAllAttractions();

    List<AttractionDto> findAttractionsByType(AttractionType type);

    List<AttractionDto> findAllByCityId(Long cityId);

    List<AttractionDto> findAllByCityName(String cityName);

    AttractionDto findAttractionById(Long attractionId);
}
