package com.shuman.tracksdemo.service;

import com.shuman.tracksdemo.model.Car;
import com.shuman.tracksdemo.model.Track;
import com.shuman.tracksdemo.repository.CarRepository;
import com.shuman.tracksdemo.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Car createCar(UUID trackId, Car carToCreate) {
        if (trackId == null) {
            throw new IllegalArgumentException("Please provide track ID");
        }

        Track track = trackRepository.findById(trackId)
            .orElseThrow(() -> new IllegalStateException(String.format("Track with provided ID [%s] is not present",
                trackId.toString())));

        carToCreate.setTrack(track);

        return carRepository.save(carToCreate);
    }

    public Car updateCar(Car carToUpdate) {
        if (!carRepository.existsById(carToUpdate.getId())) {
            throw new IllegalStateException(String.format("Car with provided ID [%s] is not present",
                carToUpdate.getId().toString()));
        }

        return carRepository.save(carToUpdate);
    }

    public void removeCar(UUID carToRemove) {
        carRepository.deleteById(carToRemove);
    }
}
