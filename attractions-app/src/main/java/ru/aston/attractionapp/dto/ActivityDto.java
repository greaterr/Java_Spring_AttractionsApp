package ru.aston.attractionapp.dto;

import lombok.*;
import ru.aston.attractionapp.entity.Attraction;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
public class ActivityDto {
    private Long id;
    private String name;
    private String description;
    private List<Long> attractionsIds;

    public void setAttractionsIds(List<Attraction> attractions) {
        this.attractionsIds = new ArrayList<>();
        for (Attraction attraction : attractions) {
            this.attractionsIds.add(attraction.getAttractionId());
        }
    }
}
