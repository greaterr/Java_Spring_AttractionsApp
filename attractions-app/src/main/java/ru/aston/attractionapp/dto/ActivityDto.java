package ru.aston.attractionapp.dto;

import lombok.Data;
import ru.aston.attractionapp.entity.Attraction;

import java.util.List;

@Data
public class ActivityDto {
    private Long id;
    private String name;
    private String description;
    private List<Attraction> attractionList;
}
