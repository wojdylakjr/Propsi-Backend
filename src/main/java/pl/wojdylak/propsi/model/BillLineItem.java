package pl.wojdylak.propsi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bill_line_item")
public class BillLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name ="unit")
    private String unit;

    @JsonIgnoreProperties("billLineItems")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id")
    private Bill bill;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public void addBill(Bill bill) {
        this.bill = bill;
        bill.getBillLineItems().add(this);
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillLineItem)) {
            return false;
        }
        return id != null && id.equals(((BillLineItem) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
