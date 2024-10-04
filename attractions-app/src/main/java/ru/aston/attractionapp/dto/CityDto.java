package ru.aston.attractionapp.dto;

import lombok.*;
import ru.aston.attractionapp.entity.Attraction;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
public class CityDto {
    private Long CityId;
    private String name;
    private int population;
    private boolean hasMetro;
    private List<AttractionDto> attractions;

 /*   public void setAttractionsIds(List<Attraction> attractions) {
        this.attractionsIds = new ArrayList<>();
        for (Attraction  attraction  : attractions) {
            this.attractionsIds.add(attraction.getAttractionId());
        }
    }

  */
}
