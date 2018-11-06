package com.shuman.tracksdemo.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Track {
    private UUID id;
    private String name;
    private String description;
    private LengthUnit length;
    private List<Car> cars;
}
