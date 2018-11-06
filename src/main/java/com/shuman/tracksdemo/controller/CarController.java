package com.shuman.tracksdemo.controller;

import com.shuman.tracksdemo.model.Car;
import com.shuman.tracksdemo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @PutMapping("{id}")
    public Car updateTrack(@PathVariable("id") UUID carId, @RequestBody Car car) {
        if (!Objects.equals(carId, car.getId())) {
            throw new IllegalArgumentException("Path 'ID' and car 'ID' do not match");
        }

        return carService.updateCar(car);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") UUID carId) {
        carService.removeCar(carId);
        return ResponseEntity.ok().build();
    }
}
