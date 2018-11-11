package com.shuman.tracksdemo.service;

import com.shuman.tracksdemo.model.Car;
import com.shuman.tracksdemo.model.Track;
import com.shuman.tracksdemo.repository.CarRepository;
import com.shuman.tracksdemo.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CarService {

    private CarRepository carRepository;

    private TrackRepository trackRepository;

    @Autowired
    public CarService(CarRepository carRepository, TrackRepository trackRepository) {
        this.carRepository = carRepository;
        this.trackRepository = trackRepository;
    }

    public List<Car> getCars(UUID trackId) {
        return carRepository.findByTrackId(trackId);
    }

    public Car createCar(UUID trackId, Car carToCreate) {
        return trackRepository.findById(trackId).map(track -> {
            carToCreate.setTrack(track);
            return carRepository.save(carToCreate);
        }).orElseThrow(() -> new IllegalArgumentException("Track ID " + trackId + " not found"));
    }

    public Car updateCar(Car carToUpdate) {
        Car carFromDb = carRepository.findById(carToUpdate.getId())
                .orElseThrow(() -> new IllegalStateException(String.format("Car with provided ID [%s] is not present",
                                                                           carToUpdate.getId().toString())));

        carToUpdate.setTrack(carFromDb.getTrack());

        return carRepository.save(carToUpdate);
    }

    public void removeCar(UUID carToRemove) {
        carRepository.deleteById(carToRemove);
    }
}
