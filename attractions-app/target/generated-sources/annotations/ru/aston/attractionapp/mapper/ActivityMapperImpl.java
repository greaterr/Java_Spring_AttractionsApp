package ru.aston.attractionapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import ru.aston.attractionapp.dto.ActivityDto;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.dto.CityDto;
import ru.aston.attractionapp.entity.Activity;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.City;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-19T17:38:11+0500",
    comments = "version: 1.6.0.RC1, compiler: javac, environment: Java 21.0.4 (Ubuntu)"
)
public class ActivityMapperImpl implements ActivityMapper {

    @Override
    public ActivityDto toActivityDto(Activity activity) {
        if ( activity == null ) {
            return null;
        }

        ActivityDto activityDto = new ActivityDto();

        activityDto.setActivityId( activity.getActivityId() );
        activityDto.setName( activity.getName() );
        activityDto.setDescription( activity.getDescription() );

        return activityDto;
    }

    @Override
    public Activity toActivityEntity(ActivityDto activityDto) {
        if ( activityDto == null ) {
            return null;
        }

        Activity activity = new Activity();

        activity.setActivityId( activityDto.getActivityId() );
        activity.setName( activityDto.getName() );
        activity.setDescription( activityDto.getDescription() );
        activity.setAttractions( attractionDtoListToAttractionList( activityDto.getAttractions() ) );

        return activity;
    }

    protected City cityDtoToCity(CityDto cityDto) {
        if ( cityDto == null ) {
            return null;
        }

        City city = new City();

        city.setCityId( cityDto.getCityId() );
        city.setName( cityDto.getName() );
        city.setPopulation( cityDto.getPopulation() );
        city.setHasMetro( cityDto.isHasMetro() );
        city.setAttractions( attractionDtoListToAttractionList( cityDto.getAttractions() ) );

        return city;
    }

    protected List<Activity> activityDtoListToActivityList(List<ActivityDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Activity> list1 = new ArrayList<Activity>( list.size() );
        for ( ActivityDto activityDto : list ) {
            list1.add( toActivityEntity( activityDto ) );
        }

        return list1;
    }

    protected Attraction attractionDtoToAttraction(AttractionDto attractionDto) {
        if ( attractionDto == null ) {
            return null;
        }

        Attraction attraction = new Attraction();

        attraction.setAttractionId( attractionDto.getAttractionId() );
        attraction.setName( attractionDto.getName() );
        attraction.setCreationDate( attractionDto.getCreationDate() );
        attraction.setDescription( attractionDto.getDescription() );
        attraction.setType( attractionDto.getType() );
        attraction.setCity( cityDtoToCity( attractionDto.getCity() ) );
        attraction.setActivities( activityDtoListToActivityList( attractionDto.getActivities() ) );

        return attraction;
    }

    protected List<Attraction> attractionDtoListToAttractionList(List<AttractionDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Attraction> list1 = new ArrayList<Attraction>( list.size() );
        for ( AttractionDto attractionDto : list ) {
            list1.add( attractionDtoToAttraction( attractionDto ) );
        }

        return list1;
    }
}
