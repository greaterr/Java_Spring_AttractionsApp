package ru.aston.attractionapp.utils;

import ru.aston.attractionapp.dto.ActivityDto;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.dto.CityDto;
import ru.aston.attractionapp.entity.Activity;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.City;

public class MappingUtils {
    public static AttractionDto mapToAttractionDto(Attraction attraction) {
    AttractionDto attractionDto = new AttractionDto();
    attractionDto.setId(attraction.getAttractionId());
    attractionDto.setName(attraction.getName());
    attractionDto.setCityId(attraction.getCity());
    attractionDto.setDescripton(attraction.getDescription());
    attractionDto.setType(attraction.getType());
    attractionDto.setActivityIds(attraction.getActivities());
    return attractionDto;
    }

    public static CityDto mapToCityDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getCityId());
        cityDto.setPopulation(city.getPopulation());
        cityDto.setName(city.getName());
        cityDto.setHasMetro(city.isHasMetro());
        cityDto.setAttractionsIds(city.getAttractions());
        return cityDto;
    }

    public static ActivityDto mapToActivityDto (Activity activity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getActivityId());
        activityDto.setName(activity.getName());
        activityDto.setDescription(activity.getDescription());
        activityDto.setAttractionsIds(activity.getAttractionList());
        return activityDto;
    }
}
