package com.neuner.cr_api.rental.service;

import com.neuner.cr_api.rental.model.Rental;
import com.neuner.cr_api.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RentalService {
    private final RentalRepository rentalRepository;

    public void createRental(Rental rental) {
        log.info("Creating rental");
        rentalRepository.save(rental);
    }

    public Rental getRental(Long id) {
        log.info("Reading rental");
        return rentalRepository.findById(id).orElse(null);
    }

    public void updateRental(Rental rental) {
        log.info("Updating rental");
        rentalRepository.save(rental);
    }

    public void deleteRental(Long id) {
        log.info("Deleting rental");
        rentalRepository.deleteById(id);
    }

    public List<Rental> getRentals() {
        log.info("Reading rentals");
        return rentalRepository.findAll();
    }
}
