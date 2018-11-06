package com.shuman.tracksdemo.repository;

import com.shuman.tracksdemo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
}
