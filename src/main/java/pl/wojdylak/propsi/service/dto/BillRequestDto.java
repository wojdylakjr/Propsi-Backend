package pl.wojdylak.propsi.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.wojdylak.propsi.model.Rental;

import javax.persistence.*;
import java.time.Instant;

public class BillRequestDto {
    private Double totalPrice;
    private Instant date;
    private Long rentalPremisesId;
    private Long rentalTenantId;
    private Long ownerId;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getRentalPremisesId() {
        return rentalPremisesId;
    }

    public void setRentalPremisesId(Long rentalPremisesId) {
        this.rentalPremisesId = rentalPremisesId;
    }

    public Long getRentalTenantId() {
        return rentalTenantId;
    }

    public void setRentalTenantId(Long rentalTenantId) {
        this.rentalTenantId = rentalTenantId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
