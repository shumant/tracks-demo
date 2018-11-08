package com.shuman.tracksdemo.controller;

import com.shuman.tracksdemo.model.Car;
import com.shuman.tracksdemo.model.Track;
import com.shuman.tracksdemo.service.CarService;
import com.shuman.tracksdemo.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class TrackCarController {
    private TrackService trackService;
    private CarService carService;

    @Autowired
    public TrackCarController(TrackService trackService, CarService carService) {
        this.trackService = trackService;
        this.carService = carService;
    }
    
    // tracks

    @GetMapping("/tracks")
    public List<Track> getTracks() {
        return trackService.getTracks();
    }

    @PostMapping("/tracks")
    public Track createTrack(@RequestBody Track track) {
        return trackService.createTrack(track);
    }

    @PutMapping("/tracks/{id}")
    public Track updateTrack(@PathVariable("id") UUID trackId, @RequestBody Track track) {
        track.setId(trackId);
        return trackService.updateTrack(track);
    }

    @DeleteMapping("/tracks/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") UUID trackId) {
        trackService.removeTrack(trackId);
        return ResponseEntity.ok().build();
    }
    
    // cars
    @GetMapping("/tracks/{trackId}/cars")
    public List<Car> getCars(@PathVariable("trackId") UUID trackId) {
        return carService.getCars(trackId);
    }

    @PostMapping("tracks/{trackId}/cars")
    public Car createCar(@PathVariable("trackId") UUID trackId, @RequestBody Car carToCreate) {
        return carService.createCar(trackId, carToCreate);
    }

    @PutMapping("/cars/{id}")
    public Car updateCar(@PathVariable("id") UUID carId, @RequestBody Car car) {
        car.setId(carId);

        return carService.updateCar(car);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") UUID carId) {
        carService.removeCar(carId);
        return ResponseEntity.ok().build();
    }
}
