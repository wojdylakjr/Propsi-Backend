package pl.wojdylak.propsi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "payU_payment_id")
    private String payUPaymentId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "pay_method")
    private String payMethod;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Bill bill;

    public Payment() {
    }

    public Payment( Date date, String payUPaymentId, BigDecimal amount, String payMethod, String currencyCode, String status) {
        this.date = date;
        this.payUPaymentId = payUPaymentId;
        this.amount = amount;
        this.payMethod = payMethod;
        this.currencyCode = currencyCode;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPayUPaymentId() {
        return payUPaymentId;
    }

    public void setPayUPaymentId(String payUPaymentId) {
        this.payUPaymentId = payUPaymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public void addBill(Bill bill){
        this.bill = bill;
        bill.setPayment(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        return id != null && id.equals(((Payment) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
