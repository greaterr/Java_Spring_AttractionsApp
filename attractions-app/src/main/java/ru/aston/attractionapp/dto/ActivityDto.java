package ru.aston.attractionapp.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
public class ActivityDto {
    private Long activityId;
    private String name;
    private String description;
    private List<AttractionDto> attractions;

}
