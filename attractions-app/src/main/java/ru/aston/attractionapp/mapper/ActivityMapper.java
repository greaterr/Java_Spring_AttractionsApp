package ru.aston.attractionapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.aston.attractionapp.dto.ActivityDto;
import ru.aston.attractionapp.entity.Activity;

@Mapper
public interface ActivityMapper {
    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

    @Named("noAttractions")
    @Mapping(target = "attractions", ignore = true)
    ActivityDto toActivityDto(Activity activity);
    Activity toActivityEntity(ActivityDto  activityDto);
}
