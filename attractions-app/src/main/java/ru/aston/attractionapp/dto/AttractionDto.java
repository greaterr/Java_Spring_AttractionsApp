package ru.aston.attractionapp.dto;

import lombok.*;
import ru.aston.attractionapp.entity.AttractionType;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
public class AttractionDto {
    private Long attractionId;
    private String name;
    private LocalDate creationDate;
    private String description;
    private AttractionType type;
    private CityDto city;
    private List<ActivityDto> activities;
}
