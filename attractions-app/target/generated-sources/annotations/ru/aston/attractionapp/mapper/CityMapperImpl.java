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
    date = "2024-10-16T20:30:37+0500",
    comments = "version: 1.6.0.RC1, compiler: javac, environment: Java 21.0.4 (Ubuntu)"
)
public class CityMapperImpl implements CityMapper {

    @Override
    public CityDto toCityDto(City city) {
        if ( city == null ) {
            return null;
        }

        CityDto cityDto = new CityDto();

        cityDto.setCityId( city.getCityId() );
        cityDto.setName( city.getName() );
        cityDto.setPopulation( city.getPopulation() );
        cityDto.setHasMetro( city.isHasMetro() );

        return cityDto;
    }

    @Override
    public City toCityEntity(CityDto cityDto) {
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

    protected Activity activityDtoToActivity(ActivityDto activityDto) {
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

    protected List<Activity> activityDtoListToActivityList(List<ActivityDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Activity> list1 = new ArrayList<Activity>( list.size() );
        for ( ActivityDto activityDto : list ) {
            list1.add( activityDtoToActivity( activityDto ) );
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
        attraction.setCity( toCityEntity( attractionDto.getCity() ) );
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
