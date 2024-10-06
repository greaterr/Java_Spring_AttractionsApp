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

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    @Autowired
    private final AttractionRepository attractionRepository;

    @Override
    public List<AttractionDto> findAllAttractions() {
        List<AttractionDto> collect = new ArrayList<>();
        for (Attraction attraction : this.attractionRepository.findAllByOrderByAttractionIdAsc()) {
            AttractionDto attractionDto = AttractionMapper.INSTANCE.toAttractionDto(attraction);
            collect.add(attractionDto);
        }
        return collect;
    }

    @Override
    public List<AttractionDto> findAttractionsByType(AttractionType type) {
        List<AttractionDto> collect = new ArrayList<>();
        for (Attraction attraction : this.attractionRepository.findAllByType(type)) {
            AttractionDto attractionDto = AttractionMapper.INSTANCE.toAttractionDto(attraction);
            collect.add(attractionDto);
        }
        return collect;
    }

    @Override
    public List<AttractionDto> findAllByCityId(Long cityId) {
        List<AttractionDto> collect = new ArrayList<>();
        for (Attraction attraction : this.attractionRepository.findAllByCityCityId(cityId)) {
            AttractionDto attractionDto = AttractionMapper.INSTANCE.toAttractionDto(attraction);
            collect.add(attractionDto);
        }
        return collect;
    }

    @Override
    public AttractionDto findAttractinById(Long attractionId) {
        Attraction attraction = this.attractionRepository.findById(attractionId).orElse(null);
        return AttractionMapper.INSTANCE.toAttractionDto(attraction);
    }
}
