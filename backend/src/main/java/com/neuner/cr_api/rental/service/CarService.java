package com.neuner.cr_api.rental.service;

import com.neuner.cr_api.rental.model.Car;
import com.neuner.cr_api.rental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CarService {
    private final CarRepository carRepository;

    public void createCar(Car car) {
        log.info("Creating car");
        carRepository.save(car);
    }

    public Car getCar(Long id) {
        log.info("Reading car");
        return carRepository.findById(id).orElse(null);
    }

    public void updateCar(Long id, Car car) {
        log.info("Updating car");
        car.setCarId(id);
        carRepository.save(car);
    }

    public void deleteCar(Long id) {
        log.info("Deleting car");
        carRepository.deleteById(id);
    }

    public List<Car> getCars() {
        log.info("Reading cars");
        return carRepository.findAll();
    }
}
