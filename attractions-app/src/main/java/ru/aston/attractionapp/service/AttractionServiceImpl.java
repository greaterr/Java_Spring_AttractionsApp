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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttractionServiceImpl implements AttractionService {

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
    public List<AttractionDto> findAllByCityName(String cityName) throws IllegalArgumentException {
        List<AttractionDto> collect = new ArrayList<>();
        for (Attraction attraction : this.attractionRepository.findAllByCityName(cityName)) {
            AttractionDto attractionDto = AttractionMapper.INSTANCE.toAttractionDto(attraction);
            collect.add(attractionDto);
        }
        if (collect.isEmpty()) {
            throw new IllegalArgumentException("No attractions for city " + cityName + " was found in dadtabase");
        }
        return collect;
    }

    @Override
    @Transactional
    public AttractionDto findAttractionById(Long attractionId) {
        Attraction attraction = this.attractionRepository.findById(attractionId).orElse(null);
        return AttractionMapper.INSTANCE.toAttractionDto(attraction);
    }

    @Override
    @Transactional
    public List<AttractionDto> findAllAttractionsFiltered(String orderByName, String attractionType) throws IllegalArgumentException {
        List<AttractionDto> attractions = new ArrayList<>();
        for (Attraction attraction : this.attractionRepository.findAllByOrderByAttractionIdAsc()) {
            AttractionDto attractionDto = AttractionMapper.INSTANCE.toAttractionDto(attraction);
            attractions.add(attractionDto);
        }
        if ("asc".equalsIgnoreCase(orderByName)) {
            attractions.sort(Comparator.comparing(AttractionDto::getName));
        } else if ("desc".equalsIgnoreCase(orderByName)) {
            attractions.sort(Comparator.comparing(AttractionDto::getName).reversed());
        } else if (orderByName != null) {
            throw new IllegalArgumentException("Wrong parameter orderByName <asc/desc>.");
        }
        if (attractionType != null) {
            AttractionType type = AttractionType.valueOf(attractionType.toUpperCase());
            attractions = attractions.stream()
                    .filter(attraction -> attraction.getType().equals(type))
                    .collect(Collectors.toList());
        }
        return attractions;
    }

    @Override
    @Transactional
    public AttractionDto updateAttraction(AttractionDto updatedAttraction) throws IllegalArgumentException {
        Optional<Attraction> foundAttractionOptional = attractionRepository.findById(updatedAttraction.getAttractionId());

        if (foundAttractionOptional.isPresent()) {
            Attraction foundAttraction = foundAttractionOptional.get();
            foundAttraction.setDescription(updatedAttraction.getDescription());
            attractionRepository.save(foundAttraction);
            return AttractionMapper.INSTANCE.toAttractionDto(foundAttraction);
        } else {
            throw new IllegalArgumentException("Attraction not found with ID: " + updatedAttraction.getAttractionId());
        }
    }

    @Override
    public void deleteAttractionById(long l) throws IllegalArgumentException{
        Optional<Attraction> foundAttractionOptional = attractionRepository.findById(l);
        if(foundAttractionOptional.isPresent()) {
            attractionRepository.delete(foundAttractionOptional.get());
        } else {
            throw new IllegalArgumentException("There is no attraction with id " + l + " in database");
        }
    }
}
