package com.neuner.cr_api.rental.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private Long customerId;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private LocalDate birthDate;

    private String drivingLicense;
    private String streetName;
    private String houseNumber;
    private String postCode;
    private String city;
    private String country;

    private LocalDateTime createdWhen;
}
