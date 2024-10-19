package ru.aston.attractionapp.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.Attraction;
import org.mapstruct.Mapper;


@Mapper(uses = {CityMapper.class, ActivityMapper.class})
public interface AttractionMapper {
    AttractionMapper INSTANCE = Mappers.getMapper(AttractionMapper.class);

    @Mapping(target = "city", qualifiedByName = "noAttractions")
    @Mapping(target = "activities", qualifiedByName = "noAttractions")
    AttractionDto toAttractionDto(Attraction attraction);

    Attraction toAttractionEntity(AttractionDto dto);
}
