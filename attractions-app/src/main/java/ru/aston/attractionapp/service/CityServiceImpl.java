package ru.aston.attractionapp.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.attractionapp.dto.CityDto;
import ru.aston.attractionapp.entity.City;
import ru.aston.attractionapp.mapper.CityMapper;
import ru.aston.attractionapp.repository.CityRepository;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;


    @Override
    @Transactional
    public CityDto addCity(CityDto cityDto) {
        City city = CityMapper.INSTANSE.toCityEntity(cityDto);
        City savedCity = cityRepository.save(city);
        return CityMapper.INSTANSE.toCityDto(savedCity);
    }
}
