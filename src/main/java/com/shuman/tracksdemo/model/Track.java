package com.shuman.tracksdemo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Track {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;

    @Embedded
    private LengthUnit length;

    @OneToMany
    private List<Car> cars;
}
