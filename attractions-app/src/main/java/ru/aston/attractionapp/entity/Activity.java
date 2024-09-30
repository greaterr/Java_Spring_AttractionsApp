package ru.aston.attractionapp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public void setId(Long id) {
        this.activityId = id;
    }

    public Long getId() {
        return activityId;
    }
}
