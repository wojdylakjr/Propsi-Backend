package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Premises;
import pl.wojdylak.propsi.model.Rental;
import pl.wojdylak.propsi.model.RentalId;
import pl.wojdylak.propsi.model.Tenant;
import pl.wojdylak.propsi.repository.RentalRepository;
import pl.wojdylak.propsi.service.dto.RentalRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final PremisesService premisesService;
    private final TenantService tenantService;

    public RentalService(RentalRepository rentalRepository, PremisesService premisesService, TenantService tenantService) {
        this.rentalRepository = rentalRepository;
        this.premisesService = premisesService;
        this.tenantService = tenantService;
    }

    public void addRental(RentalRequestDto rentalDto) {
        //TODO: refactor this code
        System.out.println(rentalDto);
        Optional<Premises> premisesById = premisesService.getPremisesById(rentalDto.getPremisesId());
        Optional<Tenant> tenant = tenantService.grtTenantById(rentalDto.getTenantId());
        if(premisesById.isPresent() && tenant.isPresent()) {
            RentalId rentalId = new RentalId(rentalDto.getTenantId(), rentalDto.getPremisesId());
            Rental rental = new Rental(tenant.get(), premisesById.get());
            rental.setId(rentalId);
            rental.setName(rentalDto.getName());
            rental.setRentPrice(rentalDto.getRentPrice());
            rental.setRentalStartDate(rentalDto.getRentalStartDate());
            rental.setRentalEndDate(rentalDto.getRentalEndDate());
            rental.setCostsPart(rentalDto.getCostsPart());
            rental.setPaymentDay(rentalDto.getPaymentDay());
            System.out.println("Tenant in rental: " + tenant.get());
            System.out.println("Premises in rental:"  + premisesById.get());
            System.out.println(rental);
            rentalRepository.save(rental);
        }else{
            System.out.println("Wrong ids for tenant or premises. Rental record not added");
        }
    }

    //TODO: write query for this!!!!
    public List<Rental> getAllRentalsForOwner(Long ownerId) {
        return rentalRepository.findAll()
                .stream()
                .filter(rental -> rental.getPremises().getProperty().getOwner().getId().equals(ownerId))
                .collect(Collectors.toList());
    }

}
