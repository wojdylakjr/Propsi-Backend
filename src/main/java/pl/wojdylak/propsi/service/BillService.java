package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Bill;
import pl.wojdylak.propsi.model.Rental;
import pl.wojdylak.propsi.repository.BillRepository;
import pl.wojdylak.propsi.service.dto.BillRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final RentalService rentalService;

    public BillService(BillRepository billRepository, RentalService rentalService) {
        this.billRepository = billRepository;
        this.rentalService = rentalService;
    }

    public List<Bill> getAllBills() {
        return this.billRepository.findAll();
    }

    //TODO: fix this
    public List<Bill> getAllBillsForRental(Long rentalPremisesId, long rentalTenantId) {
        return this.billRepository.findAll().stream()
                .filter(bill -> bill.getRental().getId().getPremisesId().equals(rentalPremisesId) && bill.getRental().getId().getTenantId().equals(rentalTenantId))
                .collect(Collectors.toList());
    }

    public void addBillForRental(BillRequestDto billRequestDto) {
        Rental ownerRentalById = rentalService.getOwnerRentalById(billRequestDto.getOwnerId(), billRequestDto.getRentalTenantId(), billRequestDto.getRentalPremisesId());
        if(ownerRentalById != null){
            Bill bill = new Bill();
            bill.setTotalPrice(billRequestDto.getTotalPrice());
            bill.setDate(billRequestDto.getDate());
            bill.addRental(ownerRentalById);
            this.billRepository.save(bill);
        }
    }

}
