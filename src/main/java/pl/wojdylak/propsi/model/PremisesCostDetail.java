package pl.wojdylak.propsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.wojdylak.propsi.model.enumaration.PremisesCostType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "premises_cost_detail")
public class PremisesCostDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "cost_value")
    private BigDecimal costValue;

    @Column(name = "date")
    private Instant date;

    @Column(name = "unit")
    private String unit;

    //TODO: check fetchType
    @JsonIgnoreProperties("premisesCostDetails")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "premises_cost_id")
    private PremisesCost premisesCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCostValue() {
        return costValue;
    }

    public void setCostValue(BigDecimal costValue) {
        this.costValue = costValue;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public PremisesCost getPremisesCost() {
        return premisesCost;
    }

    public void setPremisesCost(PremisesCost premisesCost) {
        this.premisesCost = premisesCost;
    }

    public void addPremisesCost(PremisesCost premisesCost) {
        this.premisesCost = premisesCost;
        premisesCost.getPremisesCostDetails().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PremisesCostDetail)) {
            return false;
        }
        return id != null && id.equals(((PremisesCostDetail) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "PremisesCostDetail{" +
                "id=" + id +
                ", costValue=" + costValue +
                ", date=" + date +
                ", unit='" + unit + '\'' +

                '}';
    }
}
