package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.*;
import pl.wojdylak.propsi.repository.BillLineItemRepository;
import pl.wojdylak.propsi.repository.BillRepository;
import pl.wojdylak.propsi.repository.PaymentRepository;
import pl.wojdylak.propsi.model.dto.BillRequestDto;
import pl.wojdylak.propsi.model.payu.PayUAddOrderResponse;
import pl.wojdylak.propsi.model.payu.PayUPaymentNotification;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final BillLineItemRepository billLineItemRepository;
    private final PaymentRepository paymentRepository;
    private final RentalService rentalService;
    private final PremisesService premisesService;
    private final PayUService payUService;

    public BillService(BillRepository billRepository, BillLineItemRepository billLineItemRepository, PaymentRepository paymentRepository, RentalService rentalService, PremisesService premisesService, PayUService payUService) {
        this.billRepository = billRepository;
        this.billLineItemRepository = billLineItemRepository;
        this.paymentRepository = paymentRepository;
        this.rentalService = rentalService;
        this.premisesService = premisesService;
        this.payUService = payUService;
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
        return this.billRepository.findAll()
                .stream()
                .filter(bill -> bill.getRental().getPremises().getProperty().getOwner().getId().equals(ownerId))
                .collect(Collectors.toList());
    }

    public List<Bill> getAllBillsForTenant(Long tenantId) {
        return this.billRepository.findAll()
                .stream()
                .filter(bill -> bill.getRental().getId().getTenantId().equals(tenantId))
                .collect(Collectors.toList());
    }

    public Bill getBillById(Long billId) {
        Optional<Bill> billById = this.billRepository.findById(billId);
        //            if(billById.get().getRental().getPremises().getProperty().getOwner().getId().equals(ownerId)){
        //                return billById.get();
        //            }
        return billById.orElse(null);
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

    public void generateBillsForRentalsInPremises(Long ownerId, Long premisesId, int monthValue) {
        Premises premises = premisesService.getOwnerPremisesId(ownerId, premisesId);
        Instant instant = Instant.now();

        for (Rental rental : premises.getRentals()) {
            BigDecimal totalBillPrice = BigDecimal.valueOf(0);
            Bill bill = new Bill(instant);
            bill.addRental(rental);
            totalBillPrice = addNewLineToBill(totalBillPrice, bill, new BillLineItem("Rent price", rental.getRentPrice(), "PLN"));
            for (PremisesCost cost : premises.getPremisesCosts()) {
                for (PremisesCostDetail costDetail : cost.getPremisesCostDetails()) {
                    if (LocalDate.ofInstant(costDetail.getDate(), ZoneId.of("Europe/Paris")).getMonthValue() == monthValue) {
                        BigDecimal dividedCostPrice = costDetail.getCostValue().multiply(BigDecimal.valueOf(rental.getCostsPart()));
                        totalBillPrice = addNewLineToBill(totalBillPrice, bill, new BillLineItem(cost.getCostType(), dividedCostPrice, costDetail.getUnit()));
                    }
                }
            }
            bill.setTotalPrice(totalBillPrice);
            this.billRepository.save(bill);
        }
    }

    private BigDecimal addNewLineToBill(BigDecimal totalBillPrice, Bill bill, BillLineItem billLineItem) {
        billLineItem.addBill(bill);
        return totalBillPrice.add(billLineItem.getPrice());
    }


    public PayUAddOrderResponse createPaymentForBill(Bill billById) {
        try {
            return this.payUService.addOrder(billById);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addPaymentResponse(PayUPaymentNotification paymentNotification) {
        String extOrderId = paymentNotification.getOrder().getExtOrderId();
        Bill bill = this.getBillById(Long.valueOf(extOrderId));
        if (bill == null) {
            System.out.println("Bill not found for id: " + extOrderId);
            return;
        }
        Payment payment = new Payment(
                paymentNotification.getLocalReceiptDateTime(),
                paymentNotification.getProperties().get(0).getValue(),
                BigDecimal.valueOf(Double.parseDouble(paymentNotification.getOrder().getPayMethod().getAmount()) / 100.0),
                paymentNotification.getOrder().getPayMethod().getType(),
                paymentNotification.getOrder().getCurrencyCode(),
                paymentNotification.getOrder().getStatus());
        payment.addBill(bill);
        this.paymentRepository.save(payment);

    }


}
