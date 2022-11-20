package pl.wojdylak.propsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "property_fixed_cost")
public class PropertyFixedCost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //TODO: Change to @Enumerated ?
    @Column(name = "cost_type")
    private String costType;

    @Column(name = "cost_value")
    private BigDecimal costValue;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public BigDecimal getCostValue() {
        return costValue;
    }

    public void setCostValue(BigDecimal costValue) {
        this.costValue = costValue;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void addProperty(Property property){
        this.property = property;
        property.getFixedCosts().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PropertyFixedCost)) {
            return false;
        }
        return id != null && id.equals(((PropertyFixedCost) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "PropertyFixedCost{" +
                "id=" + id +
                ", costType='" + costType + '\'' +
                ", costValue=" + costValue +
                '}';
    }
}