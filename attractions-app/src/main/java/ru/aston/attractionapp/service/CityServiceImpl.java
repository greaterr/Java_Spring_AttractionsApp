package ru.aston.attractionapp.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.attractionapp.dto.CityDto;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.City;
import ru.aston.attractionapp.mapper.AttractionMapper;
import ru.aston.attractionapp.mapper.CityMapper;
import ru.aston.attractionapp.repository.CityRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;


    @Override
    @Transactional
    public CityDto addCity(CityDto cityDto) throws IllegalArgumentException {
        Optional<City> existingCityByName = cityRepository.findByName(cityDto.getName());
        if (existingCityByName.isPresent()) {
            throw new IllegalArgumentException("City with name '" + cityDto.getName() + "' already exists"
                    + " and has an id: '" + existingCityByName.get().getCityId() + "'");
        }

        if (cityDto.getCityId() != null) {
            Optional<City> existingCityById = cityRepository.findById(cityDto.getCityId());
            if (existingCityById.isPresent()) {
                throw new IllegalArgumentException("City with ID '" + cityDto.getCityId() + "' already exists");
            }
        }

        City city = CityMapper.INSTANSE.toCityEntity(cityDto);
        City savedCity = cityRepository.save(city);
        return CityMapper.INSTANSE.toCityDto(savedCity);
    }

    @Override
    public CityDto updateCity(CityDto cityDto) {
        Optional<City> foundCityOptional = cityRepository.findById(cityDto.getCityId());
        if (foundCityOptional.isPresent()) {
            City updatedCity = foundCityOptional.get();
            updatedCity.setPopulation(cityDto.getPopulation());
            updatedCity.setHasMetro(cityDto.isHasMetro());
            cityRepository.save(updatedCity);
            return CityMapper.INSTANSE.toCityDto(updatedCity);
        } else {
            throw new IllegalArgumentException("City not found with ID: " + cityDto.getCityId());
        }
    }
}
