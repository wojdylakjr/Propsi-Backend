package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.propsi.model.Bill;
import pl.wojdylak.propsi.model.Rental;
import pl.wojdylak.propsi.service.BillService;
import pl.wojdylak.propsi.service.dto.BillRequestDto;
import pl.wojdylak.propsi.service.dto.MeterMeasurementRequestDto;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BillResource {
    private final BillService billService;

    public BillResource(BillService billService) {
        this.billService = billService;
    }
    @GetMapping("/bills")
    public List<Bill> getAllBills() {
        return this.billService.getAllBills();
    }

    @GetMapping("/owners/{ownerId}/rentals/{tenantId}/{premisesId}/bills")
    public List<Bill> getAllBillsForRental(@PathVariable Long ownerId, @PathVariable Long tenantId, @PathVariable Long premisesId) {
        return this.billService.getAllBillsForRental(premisesId, tenantId);
    }

    @PostMapping("/bills")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMeterMeasurement(@RequestBody BillRequestDto billRequestDto ) {
//        System.out.println(billRequestDto);
        billService.addBillForRental(billRequestDto);
    }
}