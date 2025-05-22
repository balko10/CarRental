package com.neuner.cr_api.rental.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;

    @NotEmpty(message = "Empty license plate is not allowed.")
    @Size(max = 20, message = "License plate longer than 20 characters is not allwoed.")
    @Column(name = "license_plate", nullable = false, unique = true, length = 20)
    private String licensePlate;

    @NotEmpty(message = "Empty brand is not allowed.")
    @Size(max = 100, message = "Length for brand not longer than 100 characters.")
    @Column(name = "brand", nullable = false, length = 100)
    private String brand;

    @NotEmpty(message = "Empty model is not allowed.")
    @Size(max = 100, message = "Length for model not longer than 100 characters.")
    @Column(name = "model", nullable = false, length = 100)
    private String model;

    @Min(value = 1900, message = "Year of construction must be after 1900.")
    @Column(name = "construction_year")
    private Year constructionYear;

    @Size(max = 50, message = "Farbe darf maximal 50 Zeichen lang sein.")
    @Column(name = "color", length = 50)
    private String color;

    @Size(max = 100, message = "Chassis number not longer than 100 characters.")
    @Column(name = "chassis_number", unique = true, length = 100)
    private String chassisNumber;

    @NotNull(message = "Empty mileage is not allowed.")
    @Min(value = 0, message = "Negativ mileage is not allowed.")
    @Column(name = "mileage", nullable = false)
    private Integer mileage = 0;

    @Size(max = 100, message = "Empty category is not allowed.")
    @Column(name = "category", length = 100)
    private String category;

    @NotNull(message = "Empty daily price is not allowed.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Daily price must be greater than 0.")
    @Column(name = "daily_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal dailyPrice;

    @Column(name = "image64", length = 2083)
    private String image64;

    @CreationTimestamp
    @Column(name = "created_when", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdWhen;

    @UpdateTimestamp
    @Column(name = "updated_when", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedWhen;

    @OneToMany(mappedBy = "car", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Rental> rentals = new ArrayList<>();
}
