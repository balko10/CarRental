package com.neuner.cr_api.rental.web;

import com.neuner.cr_api.rental.model.Rental;
import com.neuner.cr_api.rental.service.RentalService;
import com.neuner.cr_api.rental.web.dto.RentalDto;
import com.neuner.cr_api.rental.web.dto.RentalMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
@Slf4j
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("/create")
    public ResponseEntity<Void> createRental(@RequestBody RentalDto rental) {
        log.info("Creating rental");
        Rental rentalEntity = RentalMapper.toEntity(rental);
        rentalService.createRental(rentalEntity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getRental(@PathVariable("id") Long id) {
        log.info("Reading rental");
        Rental rental = rentalService.getRental(id);
        if (rental == null) {
            return ResponseEntity.notFound().build();
        }
        RentalDto rentalDto = RentalMapper.toDto(rental);
        return ResponseEntity.ok(rentalDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<RentalDto>> getRentals() {
        log.info("Reading rentals");
        List<Rental> rentals = rentalService.getRentals();
        List<RentalDto> rentalDtos = RentalMapper.toDtoList(rentals);
        return ResponseEntity.ok(rentalDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRental(@PathVariable("id") Long id, @RequestBody RentalDto rental) {
        log.info("Updating rental");
        Rental rentalEntity = RentalMapper.toEntity(rental);
        rentalEntity.setRentalId(id);
        rentalService.updateRental(rentalEntity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable("id") Long id) {
        log.info("Deleting rental");
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }
}
