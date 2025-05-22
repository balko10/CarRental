package com.neuner.cr_api.rental.web.dto;

import com.neuner.cr_api.rental.model.Rental;

import java.util.List;

public class RentalMapper {
    public static RentalDto toDto(Rental rental) {
        return RentalDto.builder()
                .rentalId(rental.getRentalId())
                .carId(rental.getCar().getCarId())
                .customerId(rental.getCustomer().getCustomerId())
                .carBrandAndModel(rental.getCar().getBrand() + " " + rental.getCar().getModel())
                .carLicensePlate(rental.getCar().getLicensePlate())
                .customerFullName(rental.getCustomer().getFirstname() + " " + rental.getCustomer().getLastname())
                .customerEmail(rental.getCustomer().getEmail())
                .rentalBegin(rental.getRentalBegin())
                .rentalEnd(rental.getRentalEnd())
                .returnDate(rental.getReturnDate())
                .mileageBegin(rental.getMileageBegin())
                .mileageEnd(rental.getMileageEnd())
                .description(rental.getDescription())
                .createdWhen(rental.getCreatedWhen())
                .build();
    }

    public static Rental toEntity(RentalDto rentalDto) {
        return Rental.builder()
                .rentalId(rentalDto.getRentalId())
                .build();
    }

    public static List<RentalDto> toDtoList(List<Rental> rentals) {
        return rentals.stream()
                .map(RentalMapper::toDto)
                .toList();
    }
}
