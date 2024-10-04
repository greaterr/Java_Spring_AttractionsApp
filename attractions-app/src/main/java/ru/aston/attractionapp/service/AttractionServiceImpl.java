package ru.aston.attractionapp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.AttractionType;
import ru.aston.attractionapp.mapper.AttractionMapper;
import ru.aston.attractionapp.repository.AttractionRepository;
//import ru.aston.attractionapp.utils.MappingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    @Autowired
    private final AttractionRepository attractionRepository;

    @Override
    public List<AttractionDto> findAllAttractions() {
        List<AttractionDto> collect = new ArrayList<>();
        for (Attraction attraction : this.attractionRepository.findAllByOrderByNameAsc()) {
            AttractionDto attractionDto = AttractionMapper.INSTANCE.toAttractionDto(attraction);
            collect.add(attractionDto);
        }
        return collect;
    }

    @Override
    public List<Attraction> findAttractionsByType(AttractionType type) {
        return this.attractionRepository.findAllByType(type);
    }

    @Override
    public List<Attraction> findAllByCityId(Long cityId) {
        return this.attractionRepository.findAllByCityCityId(cityId);
    }

    @Override
    public Attraction findAttractinById(Long attractionId) {
        return this.attractionRepository.findById(attractionId).orElse(null);
    }
}
