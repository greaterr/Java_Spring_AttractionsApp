package ru.aston.attractionapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cities")
public class City {

    @Id
    @GeneratedValue
    private Long cityId;

    private String name;
    private int population;
    @Column(name="has_metro")
    private boolean hasMetro;

    @OneToMany
    private List<Attraction> attractions;

    public void setId(Long id) {
        this.cityId = id;
    }

    public Long getId() {
        return cityId;
    }
}
