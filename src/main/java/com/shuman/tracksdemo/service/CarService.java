package com.shuman.tracksdemo.service;

import com.shuman.tracksdemo.model.Car;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarService {
    public Car updateCar(Car carToUpdate) {
        return carToUpdate;
    }

    public void removeCar(UUID carToRemove) {

    }
}
