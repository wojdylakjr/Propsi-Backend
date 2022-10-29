package pl.wojdylak.propsi.model;


import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


@Entity
@Table(name = "rental")
public class Rental implements Serializable {

    @EmbeddedId
    private RentalId id;

    @Column(name = "name")
    private String name;

    @Column(name = "rent_price")
    private Long rentPrice;

    @Column(name = "rental_start_date")
    private Instant rentalStartDate;

    @Column(name = "rental_end_date")
    private Instant rentalEndDate;

    @Column(name = "payment_day")
    private Integer paymentDay;

    @Column(name = "costs_part")
    private Double costsPart;

    //TODO: Change to LAZY FetchType !!!
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("tenantId")
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("premisesId")
    private Premises premises;

    public Rental() {
    }

    public Rental(Tenant tenant, Premises premises) {
        this.tenant = tenant;
        this.premises = premises;
    }

    public RentalId getId() {
        return id;
    }

    public void setId(RentalId id) {
        this.id = id;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Premises getPremises() {
        return premises;
    }

    public void setPremises(Premises premises) {
        this.premises = premises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Long rentPrice) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rental)) {
            return false;
        }
        return id != null && id.equals(((Rental) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rentPrice=" + rentPrice +
                ", rentalStartDate=" + rentalStartDate +
                ", rentalEndDate=" + rentalEndDate +
                ", paymentDay=" + paymentDay +
                ", costsPart=" + costsPart +
                ", tenant name=" + tenant.getName() +
                ", premises=" + premises.getName() +
                '}';
    }

}
