package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.propsi.model.Premises;
import pl.wojdylak.propsi.model.Rental;
import pl.wojdylak.propsi.repository.RentalRepository;
import pl.wojdylak.propsi.service.RentalService;
import pl.wojdylak.propsi.service.dto.RentalRequestDto;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalResource {

    private final RentalRepository rentalRepository;
    private final RentalService rentalService;

    public RentalResource(RentalRepository rentalRepository, RentalService rentalService) {
        this.rentalRepository = rentalRepository;
        this.rentalService = rentalService;
    }

    @PostMapping("/rentals")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRental(@RequestBody RentalRequestDto rental) {
        System.out.println(rental);
        rentalService.addRental(rental);
    }

    @GetMapping("/rentals")
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @GetMapping("/owners/{ownerId}/rentals")
    public List<Rental> getAllRentalsForOwner(@PathVariable Long ownerId) {
        return this.rentalService.getAllRentalsForOwner(ownerId);
    }

    @GetMapping("/owners/{ownerId}/rentals/{tenantId}/{premisesId}")
    public ResponseEntity<Rental> getOwnerRentalById(@PathVariable Long ownerId,@PathVariable Long tenantId,@PathVariable Long premisesId) {
        Rental ownerRentalById = this.rentalService.getOwnerRentalById(ownerId, tenantId, premisesId);
        if(ownerRentalById == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ownerRentalById, HttpStatus.OK);
    }
}
