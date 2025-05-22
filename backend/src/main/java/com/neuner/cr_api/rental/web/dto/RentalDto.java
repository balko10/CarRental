package com.neuner.cr_api.rental.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalDto {

    private Long rentalId;

    private Long carId;
    private String carLicensePlate;
    private String carBrandAndModel;

    private Long customerId;
    private String customerFullName;
    private String customerEmail;

    private LocalDateTime rentalBegin;
    private LocalDateTime rentalEnd;
    private LocalDateTime returnDate;

    private Integer mileageBegin;
    private Integer mileageEnd;

    private String description;
    private LocalDateTime createdWhen;
}
