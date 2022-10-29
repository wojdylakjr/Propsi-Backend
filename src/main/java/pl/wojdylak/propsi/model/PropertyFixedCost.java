package pl.wojdylak.propsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import pl.wojdylak.propsi.model.enumaration.PropertyFixedCostsType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "property_fixed_cost")
public class PropertyFixedCost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "cost_type")
    private PropertyFixedCostsType costType;

    @Column(name = "cost_value")
    private Double costValue;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PropertyFixedCostsType getCostType() {
        return costType;
    }

    public void setCostType(PropertyFixedCostsType costType) {
        this.costType = costType;
    }

    public Double getCostValue() {
        return costValue;
    }

    public void setCostValue(Double costValue) {
        this.costValue = costValue;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
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
}