package com.neuner.cr_api.rental.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @NotEmpty(message = "Empty firstname is not allowed.")
    @Size(max = 100, message = "Firstname not longer than 100 characters.")
    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;

    @NotEmpty(message = "Empty lastname is not allowed.")
    @Size(max = 100, message = "Lastname not longer than 100 characters.")
    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @NotEmpty(message = "Empty email is not allowed.")
    @Email(message = "Email is not valid.")
    @Size(max = 255, message = "Email not longer than 255 characters.")
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Size(max = 50, message = "Phone number not longer than 50 characters.")
    @Column(name = "phone", length = 50)
    private String phone;

    @Past(message = "Birthdate must be in the past.")
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @NotEmpty(message = "Empty driving license is not allowed.")
    @Size(max = 100, message = "Driving license not longer than 100 characters.")
    @Column(name = "driving_license", unique = true, nullable = false, length = 100)
    private String drivingLicense;

    @Size(max = 255, message = "Street name not longer than 255 characters.")
    @Column(name = "street_name", length = 255)
    private String streetName;

    @Size(max = 10, message = "House number not longer than 10 characters.")
    @Column(name = "house_number", length = 10)
    private String houseNumber;

    @Size(max = 10, message = "Postcode not longer than 10 characters.")
    @Column(name = "post_code", length = 10)
    private String postCode;

    @Size(max = 100, message = "City not longer than 100 characters.")
    @Column(name = "city", length = 100)
    private String city;

    @Size(max = 100, message = "Country not longer than 100 characters.")
    @Column(name = "country", length = 100)
    private String country;

    @CreationTimestamp
    @Column(name = "created_when", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdWhen;

    @UpdateTimestamp
    @Column(name = "updated_when", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedWhen;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Rental> rentals = new ArrayList<>();

}
