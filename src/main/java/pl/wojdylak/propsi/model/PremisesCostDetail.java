package pl.wojdylak.propsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.wojdylak.propsi.model.enumaration.PremisesCostType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "premises_cost_detail")
public class PremisesCostDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "cost_value")
    private Double costValue;

    @Column(name = "date")
    private Instant date;

    @Column(name = "unit")
    private String unit;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "premises_cost_id")
    private PremisesCost premisesCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCostValue() {
        return costValue;
    }

    public void setCostValue(Double costValue) {
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
}
