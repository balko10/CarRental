package com.neuner.cr_api.rental.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {

    private Long carId;
    private String licensePlate;
    private String brand;
    private String model;
    private String constructionYear;
    private String color;
    private String chassisNumber;
    private Integer mileage;
    private String category;
    private BigDecimal dailyPrice;
    private String image64;
}
