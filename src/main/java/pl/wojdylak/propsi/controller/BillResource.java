package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.propsi.model.Bill;
import pl.wojdylak.propsi.service.BillService;
import pl.wojdylak.propsi.service.dto.BillRequestDto;
import pl.wojdylak.propsi.model.payu.PayUAddOrderResponse;

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

    @GetMapping("/owners/{ownerId}/bills")
    public List<Bill> getAllBillsForOwner(@PathVariable Long ownerId) {
        return this.billService.getAllBillsForOwner(ownerId);
    }

    @GetMapping("/owners/{ownerId}/bills/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long ownerId, @PathVariable Long billId) {
        //TODO: with or without owner id?
        Bill billById = this.billService.getBillById( billId);
        if (billById != null) {
            return new ResponseEntity<>(billById, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/owners/{ownerId}/bills/{billId}/pay")
    public ResponseEntity<PayUAddOrderResponse> createPaymentForBill(@PathVariable Long ownerId, @PathVariable Long billId) {
//        TODO: with or without owner id?
        Bill billById = this.billService.getBillById(billId);
        if (billById != null) {
            PayUAddOrderResponse paymentForBill = this.billService.createPaymentForBill(billById);
            if (paymentForBill != null) {
                return new ResponseEntity<>(paymentForBill, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/bills")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMeterMeasurement(@RequestBody BillRequestDto billRequestDto) {
//        System.out.println(billRequestDto);
        billService.addBillForRental(billRequestDto);
    }

    @PostMapping("/owners/{ownerId}/premises/{premisesId}/bills")
    @ResponseStatus(HttpStatus.CREATED)
    public void generateBillsForRentalsInPremises(@PathVariable Long ownerId, @PathVariable Long premisesId) {
//        System.out.println(billRequestDto);
        billService.generateBillsForRentalsInPremises(ownerId, premisesId);
    }
}