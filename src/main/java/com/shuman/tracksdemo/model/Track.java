package com.shuman.tracksdemo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Track {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    @Embedded
    private LengthUnit length;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "track")
    @Getter(AccessLevel.NONE)
    private List<Car> cars;

    /*public void addCar(Car car) {
        if (cars == null) {
            cars = new ArrayList<>();
        }

        car.setTrack(this);
        cars.add(car);
    }*/

    /**
     * @return immutable list of cars so we can only persist {@link Car} via {@link com.shuman.tracksdemo.service.CarService}
     */
    public List<Car> getCars() {
        return List.copyOf(cars);
    }
}
