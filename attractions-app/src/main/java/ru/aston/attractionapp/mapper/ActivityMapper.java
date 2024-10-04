package ru.aston.attractionapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.aston.attractionapp.dto.ActivityDto;
import ru.aston.attractionapp.entity.Activity;

@Mapper
public interface ActivityMapper {
    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);
    ActivityDto toActivityDto(Activity activity);
    Activity toActivityEntity(ActivityDto  activityDto);
}
