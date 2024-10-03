package ru.aston.attractionapp.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="activities")
public class Activity {
    @Id
    @GeneratedValue
    private Long activityId;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "activities")
    private List<Attraction> attractionList;

}
