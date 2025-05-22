package com.neuner.cr_api.rental.web;

import com.neuner.cr_api.rental.model.Car;
import com.neuner.cr_api.rental.service.CarService;
import com.neuner.cr_api.rental.web.dto.CarDto;
import com.neuner.cr_api.rental.web.dto.CarMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@Slf4j
public class CarController {
    private final CarService carService;

    @PostMapping("/create")
    public ResponseEntity<Void> createCar(@RequestBody CarDto car) {
        log.info("Creating car");
        Car carEntity = CarMapper.toEntity(car);
        carService.createCar(carEntity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CarDto>> getCars() {
        log.info("Reading cars");
        List<Car> cars = carService.getCars();
        List<CarDto> carDtos = CarMapper.toDtoList(cars);
        return ResponseEntity.ok(carDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable("id") Long id) {
        log.info("Reading car");
        Car car = carService.getCar(id);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        CarDto carDto = CarMapper.toDto(car);
        return ResponseEntity.ok(carDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCar(@PathVariable("id") Long id, @RequestBody CarDto car) {
        log.info("Updating car");
        Car carEntity = CarMapper.toEntity(car);
        carEntity.setCarId(id);
        carService.updateCar(id, carEntity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Long id) {
        log.info("Deleting car");
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
