package ru.aston.attractionapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.aston.attractionapp.dto.CityDto;
import ru.aston.attractionapp.entity.City;

@Mapper(uses = AttractionMapper.class)
public interface CityMapper {
    CityMapper INSTANSE = Mappers.getMapper(CityMapper.class);

    @Named("noAttractions")
    @Mapping(target = "attractions", ignore = true)
    CityDto toCityDto(City city);
    City toCityEntity(CityDto cityDto);
}
