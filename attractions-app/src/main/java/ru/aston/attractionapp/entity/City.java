package ru.aston.attractionapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    private String name;
    private int population;
    @Column(name="has_metro")
    private boolean hasMetro;

    @OneToMany (mappedBy = "city", cascade = CascadeType.ALL)
    private List<Attraction> attractions;


}
