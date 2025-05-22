package com.neuner.cr_api.rental.web.dto;

import com.neuner.cr_api.rental.model.Customer;

import java.util.List;

public class CustomerMapper {
    public static CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .birthDate(customer.getBirthDate())
                .drivingLicense(customer.getDrivingLicense())
                .streetName(customer.getStreetName())
                .houseNumber(customer.getHouseNumber())
                .postCode(customer.getPostCode())
                .city(customer.getCity())
                .country(customer.getCountry())
                .createdWhen(customer.getCreatedWhen())
                .build();
    }

    public static Customer toEntity(CustomerDto customerDto) {
        return Customer.builder()
                .customerId(customerDto.getCustomerId())
                .firstname(customerDto.getFirstname())
                .lastname(customerDto.getLastname())
                .email(customerDto.getEmail())
                .phone(customerDto.getPhone())
                .birthDate(customerDto.getBirthDate())
                .drivingLicense(customerDto.getDrivingLicense())
                .streetName(customerDto.getStreetName())
                .houseNumber(customerDto.getHouseNumber())
                .postCode(customerDto.getPostCode())
                .city(customerDto.getCity())
                .country(customerDto.getCountry())
                .build();
    }

    public static List<CustomerDto> toDtoList(List<Customer> customers) {
        return customers.stream()
                .map(CustomerMapper::toDto)
                .toList();
    }
}
