package ru.aston.attractionapp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.AttractionType;
import ru.aston.attractionapp.mapper.AttractionMapper;
import ru.aston.attractionapp.repository.AttractionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    @Autowired
    private final AttractionRepository attractionRepository;

    @Override
    @Transactional
    public AttractionDto addAttraction(AttractionDto attractionDto) throws IllegalArgumentException {
        Optional<Attraction> existingAttraction;
        if (attractionDto.getCity() != null) {
            existingAttraction = attractionRepository.findByNameAndCityCityId(attractionDto.getName(), attractionDto.getCity().getCityId());
        } else {
            existingAttraction = attractionRepository.findByNameAndCityIsNull(attractionDto.getName());
        }

        if (existingAttraction.isPresent()) {
            throw new IllegalArgumentException("Attraction with name '" + attractionDto.getName() +
                    "' already exists in the specified city or without a city");
        }

        if (attractionDto.getAttractionId() != null) {
            Optional<Attraction> existingAttractionById = attractionRepository.findById(attractionDto.getAttractionId());
            if (existingAttractionById.isPresent()) {
                throw new IllegalArgumentException("Attraction with id '" + attractionDto.getAttractionId() +
                        "' already exists");
            }
        }

        Attraction attraction = AttractionMapper.INSTANCE.toAttractionEntity(attractionDto);
        Attraction savedAttraction = attractionRepository.save(attraction);

        return AttractionMapper.INSTANCE.toAttractionDto(savedAttraction);
    }



    @Override
    @Transactional
    public List<AttractionDto> findAllAttractions() {
        List<AttractionDto> collect = new ArrayList<>();
        for (Attraction attraction : this.attractionRepository.findAllByOrderByAttractionIdAsc()) {
            AttractionDto attractionDto = AttractionMapper.INSTANCE.toAttractionDto(attraction);
            collect.add(attractionDto);
        }
        return collect;
    }

    @Override
    @Transactional
    public List<AttractionDto> findAttractionsByType(AttractionType type) {
        List<AttractionDto> collect = new ArrayList<>();
        for (Attraction attraction : this.attractionRepository.findAllByType(type)) {
            AttractionDto attractionDto = AttractionMapper.INSTANCE.toAttractionDto(attraction);
            collect.add(attractionDto);
        }
        return collect;
    }

    @Override
    @Transactional
    public List<AttractionDto> findAllByCityId(Long cityId) {
        List<AttractionDto> collect = new ArrayList<>();
        for (Attraction attraction : this.attractionRepository.findAllByCityCityId(cityId)) {
            AttractionDto attractionDto = AttractionMapper.INSTANCE.toAttractionDto(attraction);
            collect.add(attractionDto);
        }
        return collect;
    }

    @Override
    @Transactional
    public List<AttractionDto> findAllByCityName(String cityName) {
        List<AttractionDto> collect = new ArrayList<>();
        for (Attraction attraction : this.attractionRepository.findAllByCityName(cityName)) {
            AttractionDto attractionDto = AttractionMapper.INSTANCE.toAttractionDto(attraction);
            collect.add(attractionDto);
        }
        return collect;
    }

    @Override
    @Transactional
    public AttractionDto findAttractionById(Long attractionId) {
        Attraction attraction = this.attractionRepository.findById(attractionId).orElse(null);
        return AttractionMapper.INSTANCE.toAttractionDto(attraction);
    }
}
