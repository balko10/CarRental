package com.neuner.cr_api.rental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Long rentalId;

    @NotNull(message = "Empty car is not allowed.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @NotNull(message = "Empty customer is not allowed.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull(message = "Empty rental begin date is not allowed.")
    @Column(name = "rental_begin", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime rentalBegin;

    @NotNull(message = "Empty rental end date is not allowed.")
    @Column(name = "rental_end", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime rentalEnd;

    @Column(name = "return_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime returnDate;

    @NotNull(message = "Empty mileage begin is not allowed.")
    @Min(value = 0, message = "Negativ mileage begin is not allowed.")
    @Column(name = "mileage_begin", nullable = false)
    private Integer mileageBegin;

    @Min(value = 0, message = "Negativ mileage end is not allowed.")
    @Column(name = "mileage_end")
    private Integer mileageEnd;

    @Column(name = "total_costs", precision = 10, scale = 2)
    private BigDecimal totalCosts;

    @NotEmpty(message = "Empty rental status is not allowed.")
    @Size(max = 50, message = "Rental status is too long.")
    @Column(name = "rental_Status", nullable = false, length = 50)
    private String rentalStatus = "aktiv";

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(name = "created_when", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdWhen;

    @UpdateTimestamp
    @Column(name = "updated_when", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedWhen;

}
