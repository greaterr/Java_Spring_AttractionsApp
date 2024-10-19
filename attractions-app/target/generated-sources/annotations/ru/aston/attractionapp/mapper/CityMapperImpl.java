package ru.aston.attractionapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.dto.CityDto;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.City;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-19T17:16:35+0500",
    comments = "version: 1.6.0.RC1, compiler: javac, environment: Java 21.0.4 (Ubuntu)"
)
public class CityMapperImpl implements CityMapper {

    private final AttractionMapper attractionMapper = AttractionMapper.INSTANCE;

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

    protected List<Attraction> attractionDtoListToAttractionList(List<AttractionDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Attraction> list1 = new ArrayList<Attraction>( list.size() );
        for ( AttractionDto attractionDto : list ) {
            list1.add( attractionMapper.toAttractionEntity( attractionDto ) );
        }

        return list1;
    }
}
