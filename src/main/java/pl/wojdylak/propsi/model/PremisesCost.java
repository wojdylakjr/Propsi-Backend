package pl.wojdylak.propsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.wojdylak.propsi.model.enumaration.MeterType;
import pl.wojdylak.propsi.model.enumaration.PremisesCostType;
import pl.wojdylak.propsi.model.enumaration.PropertyFixedCostsType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "premises_cost")
public class PremisesCost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

//    back to enum?
    @Column(name = "cost_type")
    private String costType;

    //TODO: check it
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "premises_id")
    private Premises premises;

    @JsonIgnore
    @OneToMany(mappedBy = "premisesCost", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PremisesCostDetail> premisesCostDetails = new HashSet<>();

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

    public Set<PremisesCostDetail> getPremisesCostDetails() {
        return premisesCostDetails;
    }

    public void setPremisesCostDetails(Set<PremisesCostDetail> premisesCostDetails) {
        this.premisesCostDetails = premisesCostDetails;
    }

    public Premises getPremises() {
        return premises;
    }

    public void setPremises(Premises premises) {
        this.premises = premises;
    }

//    public void addPremises(Premises premises){
//        this.premises = premises;
//        premises.getPremisesCosts().add(this);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PremisesCost)) {
            return false;
        }
        return id != null && id.equals(((PremisesCost) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "PremisesCost{" +
                "id=" + id +
                ", costType=" + costType +
                ", premisesCostDetails=" + premisesCostDetails +
                '}';
    }
}
