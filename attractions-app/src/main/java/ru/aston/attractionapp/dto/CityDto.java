package ru.aston.attractionapp.dto;

import lombok.*;
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
}
