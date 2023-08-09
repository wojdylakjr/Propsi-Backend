package pl.wojdylak.propsi.model.dto;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.Instant;

public class RentalRequestDto {
    private Long tenantId;
    private Long premisesId;
    private String name;
    private BigDecimal rentPrice;
    private Instant rentalStartDate;
    private Instant rentalEndDate;
    private Integer paymentDay;
    private Double costsPart;

    public RentalRequestDto() {
    }

    public RentalRequestDto(Long tenantId, Long premisesId, String name) {
        this.tenantId = tenantId;
        this.premisesId = premisesId;
        this.name = name;
    }

    public RentalRequestDto(Long tenantId, Long premisesId, String name, BigDecimal rentPrice) {
        this.tenantId = tenantId;
        this.premisesId = premisesId;
        this.name = name;
        this.rentPrice = rentPrice;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getPremisesId() {
        return premisesId;
    }

    public void setPremisesId(Long premisesId) {
        this.premisesId = premisesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Instant getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(Instant rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public Instant getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(Instant rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public Integer getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(Integer paymentDay) {
        this.paymentDay = paymentDay;
    }

    public Double getCostsPart() {
        return costsPart;
    }

    public void setCostsPart(Double costsPart) {
        this.costsPart = costsPart;
    }

    @Override
    public String toString() {
        return "RentalRequestDto{" +
                "tenantId=" + tenantId +
                ", premisesId=" + premisesId +
                ", name='" + name + '\'' +
                ", rentPrice=" + rentPrice +
                ", rentalStartDate=" + rentalStartDate +
                ", rentalEndDate=" + rentalEndDate +
                ", paymentDay=" + paymentDay +
                ", costsPart=" + costsPart +
                '}';
    }
}
