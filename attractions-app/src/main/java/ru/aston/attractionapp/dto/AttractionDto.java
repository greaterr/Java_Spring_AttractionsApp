package ru.aston.attractionapp.dto;

import lombok.*;
import ru.aston.attractionapp.entity.Activity;
import ru.aston.attractionapp.entity.AttractionType;
import ru.aston.attractionapp.entity.City;
import ru.aston.attractionapp.utils.MappingUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
public class AttractionDto {
    private Long id;
    private String name;
    private LocalDate creationDate;
    private String descripton;
    private AttractionType type;
    private Long cityId;
    private List<Long> activitiyIds;

    public void setActivityIds(List<Activity> activities) {
        this.activitiyIds =  new ArrayList<>();
        for (Activity activity : activities) {
            activitiyIds.add(MappingUtils.mapToActivityDto(activity).getId());
        }
    }

    public void setCityId(City city) {
        this.cityId = city.getCityId();
    }
}
