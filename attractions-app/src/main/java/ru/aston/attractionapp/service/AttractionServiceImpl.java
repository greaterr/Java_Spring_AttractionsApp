package ru.aston.attractionapp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.AttractionType;
import ru.aston.attractionapp.repository.AttractionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    @Autowired
    private final AttractionRepository attractionRepository;

    @Override
    public List<Attraction> findAllAttractions() {
        return this.attractionRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Attraction> findAttractionsByType(AttractionType type) {
        return this.attractionRepository.findAllByType(type);
    }

    @Override
    public List<Attraction> findAllByCityId(Long cityId) {
        return this.attractionRepository.findAllByCityId(cityId);
    }

    @Override
    public Attraction findAttractinById(Long attractionId) {
        return this.attractionRepository.findById(attractionId).orElse(null);
    }
}
