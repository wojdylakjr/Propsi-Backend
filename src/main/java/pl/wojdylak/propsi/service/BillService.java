package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.*;
import pl.wojdylak.propsi.repository.BillLineItemRepository;
import pl.wojdylak.propsi.repository.BillRepository;
import pl.wojdylak.propsi.service.dto.BillRequestDto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final BillLineItemRepository billLineItemRepository;
    private final RentalService rentalService;
    private final PremisesService premisesService;

    public BillService(BillRepository billRepository, BillLineItemRepository billLineItemRepository, RentalService rentalService, PremisesService premisesService) {
        this.billRepository = billRepository;
        this.billLineItemRepository = billLineItemRepository;
        this.rentalService = rentalService;
        this.premisesService = premisesService;
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

    public List<Bill> getAllBillsForOwner(Long ownerId) {
        return this.billRepository.findAll().stream()
                .filter(bill -> bill.getRental().getPremises().getProperty().getOwner().getId().equals(ownerId))
                .collect(Collectors.toList());
    }

    public Bill getBillById(Long ownerId, Long billId) {
        Optional<Bill> billById = this.billRepository.findById(billId);
        if(billById.isPresent()){
            if(billById.get().getRental().getPremises().getProperty().getOwner().getId().equals(ownerId)){
                return billById.get();
            }
        }
        return null;
    }

    public void addBillForRental(BillRequestDto billRequestDto) {
        Rental ownerRentalById = rentalService.getOwnerRentalById(billRequestDto.getOwnerId(), billRequestDto.getRentalTenantId(), billRequestDto.getRentalPremisesId());
        if (ownerRentalById != null) {
            Bill bill = new Bill();
            bill.setTotalPrice(billRequestDto.getTotalPrice());
            bill.setDate(billRequestDto.getDate());
            bill.addRental(ownerRentalById);
            this.billRepository.save(bill);
        }
    }

    public void generateBillsForRentalsInPremises(Long ownerId, Long premisesId) {
        Premises premises = premisesService.getOwnerPremisesId(ownerId, premisesId);
        Set<Rental> rentals = premises.getRentals();
        Instant instant = Instant.now();

        LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.of("Europe/Paris"));
        for (Rental rental : rentals) {
            BigDecimal totalBillPrice = BigDecimal.valueOf(0);

            Bill bill = new Bill();
            bill.addRental(rental);
            bill.setDate(instant);
//            this.billRepository.saveAndFlush(bill);
            Set<BillLineItem> billLineItems = new HashSet<>();
            BillLineItem rentPriceBillLine = new BillLineItem();
            rentPriceBillLine.setPrice(rental.getRentPrice());
            rentPriceBillLine.setName("Rent price");
            rentPriceBillLine.addBill(bill);
            billLineItems.add(rentPriceBillLine);
            totalBillPrice = totalBillPrice.add(rental.getRentPrice());
//            billLineItemRepository.saveAndFlush(rentPriceBillLine);
            for (PremisesCost cost : premises.getPremisesCosts()) {
                for (PremisesCostDetail costDetail : cost.getPremisesCostDetails()) {
                    if (LocalDate.ofInstant(costDetail.getDate(), ZoneId.of("Europe/Paris")).getMonthValue() == localDate.getMonthValue()) {
                        BillLineItem billLineItem = new BillLineItem();
                        billLineItem.setName(cost.getCostType());
                        BigDecimal costPrice = costDetail.getCostValue().multiply(BigDecimal.valueOf(rental.getCostsPart()));
                        billLineItem.setPrice(costPrice);
                        billLineItem.setUnit(costDetail.getUnit());
                        billLineItem.addBill(bill);
                        billLineItems.add(billLineItem);
                        totalBillPrice = totalBillPrice.add(costPrice);
//                        this.billLineItemRepository.saveAndFlush(billLineItem);
                    }
                }
            }
            bill.setBillLineItems(billLineItems);
            bill.setTotalPrice(totalBillPrice);
            this.billRepository.save(bill);
        }
    }



}
