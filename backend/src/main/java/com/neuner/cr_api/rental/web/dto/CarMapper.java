package com.neuner.cr_api.rental.web.dto;

import com.neuner.cr_api.rental.model.Car;

import java.time.Year;
import java.util.List;

public class CarMapper {
    public static CarDto toDto(Car car) {
        return CarDto.builder()
                .carId(car.getCarId())
                .model(car.getModel())
                .category(car.getCategory())
                .dailyPrice(car.getDailyPrice())
                .brand(car.getBrand())
                .constructionYear(String.valueOf(car.getConstructionYear()))
                .mileage(car.getMileage())
                .color(car.getColor())
                .licensePlate(car.getLicensePlate())
                .build();
    }

    public static Car toEntity(CarDto carDto) {
        return Car.builder()
                .carId(carDto.getCarId())
                .model(carDto.getModel())
                .dailyPrice(carDto.getDailyPrice())
                .category(carDto.getCategory())
                .brand(carDto.getBrand())
                .constructionYear(Year.of(Integer.parseInt(carDto.getConstructionYear())))
                .mileage(carDto.getMileage())
                .color(carDto.getColor())
                .licensePlate(carDto.getLicensePlate())
                .build();
    }

    public static List<CarDto> toDtoList(List<Car> cars) {
        return cars.stream()
                .map(CarMapper::toDto)
                .toList();
    }
}
