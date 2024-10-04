package ru.aston.attractionapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.aston.attractionapp.dto.CityDto;
import ru.aston.attractionapp.entity.City;

@Mapper
public interface CityMapper {
    CityMapper INSTANSE = Mappers.getMapper(CityMapper.class);

    @Mapping(source = "attractions", target = "attractions", ignore = true)
    CityDto toCityDto(City city);
    City toCityEntity(CityDto cityDto);
}
