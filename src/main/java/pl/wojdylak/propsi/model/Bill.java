package pl.wojdylak.propsi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bill")
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "date")
    private Instant date;


    //TODO: Change to lazy
    @JsonIgnoreProperties(value = "bill", allowSetters = true)
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "bill", fetch = FetchType.EAGER)
    private Payment payment;

    //TODO: lazy
    @JsonIgnoreProperties({"bills"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name="rental_premises_id"),
            @JoinColumn(name="rental_tenant_id")
    })
    private Rental rental;

    @JsonIgnoreProperties("bill")
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private Set<BillLineItem> billLineItems = new HashSet<>();

    public Bill() {
    }

    public Bill(Instant date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public void addRental(Rental rental){
        this.rental = rental;
        rental.getBills().add(this);
    }

    public Set<BillLineItem> getBillLineItems() {
        return billLineItems;
    }

    public void setBillLineItems(Set<BillLineItem> billLineItems) {
        this.billLineItems = billLineItems;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bill)) {
            return false;
        }
        return id != null && id.equals(((Bill) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
