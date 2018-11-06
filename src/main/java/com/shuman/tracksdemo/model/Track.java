package com.shuman.tracksdemo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Track {
    private UUID id;
    private String name;
    private String description;
    private LengthUnit length;

    @OneToMany
    private List<Car> cars;
}
