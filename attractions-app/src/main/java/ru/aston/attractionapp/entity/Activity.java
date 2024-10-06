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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "activities")
    private List<Attraction> attractions;
}
