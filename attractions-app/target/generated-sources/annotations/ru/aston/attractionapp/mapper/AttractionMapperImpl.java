package ru.aston.attractionapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.mapstruct.factory.Mappers;
import ru.aston.attractionapp.dto.ActivityDto;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.Activity;
import ru.aston.attractionapp.entity.Attraction;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-19T14:17:51+0500",
    comments = "version: 1.6.0.RC1, compiler: javac, environment: Java 21.0.4 (Ubuntu)"
)
public class AttractionMapperImpl implements AttractionMapper {

    private final CityMapper cityMapper = Mappers.getMapper( CityMapper.class );
    private final ActivityMapper activityMapper = ActivityMapper.INSTANCE;

    @Override
    public AttractionDto toAttractionDto(Attraction attraction) {
        if ( attraction == null ) {
            return null;
        }

        AttractionDto attractionDto = new AttractionDto();

        attractionDto.setCity( cityMapper.toCityDto( attraction.getCity() ) );
        attractionDto.setActivities( activityListToActivityDtoList( attraction.getActivities() ) );
        attractionDto.setAttractionId( attraction.getAttractionId() );
        attractionDto.setName( attraction.getName() );
        attractionDto.setCreationDate( attraction.getCreationDate() );
        attractionDto.setDescription( attraction.getDescription() );
        attractionDto.setType( attraction.getType() );

        return attractionDto;
    }

    @Override
    public Attraction toAttractionEntity(AttractionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Attraction attraction = new Attraction();

        attraction.setAttractionId( dto.getAttractionId() );
        attraction.setName( dto.getName() );
        attraction.setCreationDate( dto.getCreationDate() );
        attraction.setDescription( dto.getDescription() );
        attraction.setType( dto.getType() );
        attraction.setCity( cityMapper.toCityEntity( dto.getCity() ) );
        attraction.setActivities( activityDtoListToActivityList( dto.getActivities() ) );

        return attraction;
    }

    protected List<ActivityDto> activityListToActivityDtoList(List<Activity> list) {
        if ( list == null ) {
            return null;
        }

        List<ActivityDto> list1 = new ArrayList<ActivityDto>( list.size() );
        for ( Activity activity : list ) {
            list1.add( activityMapper.toActivityDto( activity ) );
        }

        return list1;
    }

    protected List<Activity> activityDtoListToActivityList(List<ActivityDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Activity> list1 = new ArrayList<Activity>( list.size() );
        for ( ActivityDto activityDto : list ) {
            list1.add( activityMapper.toActivityEntity( activityDto ) );
        }

        return list1;
    }
}
