package ru.aston.attractionapp.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.Attraction;
import org.mapstruct.Mapper;


@Mapper(uses = {CityMapper.class, ActivityMapper.class})
public interface AttractionMapper {
    AttractionMapper INSTANCE = Mappers.getMapper(AttractionMapper.class);
    @Mapping(source = "attractionId", target = "attractionId")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "activities", target = "activities", ignore = true)
    AttractionDto toAttractionDto(Attraction attraction);
    @Mapping(source = "city", target = "city")
    @Mapping(source = "activities", target = "activities", ignore = true)
    Attraction toAttractionEntity(AttractionDto dto);
}
