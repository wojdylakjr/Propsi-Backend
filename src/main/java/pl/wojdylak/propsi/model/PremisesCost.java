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

    @Enumerated(EnumType.STRING)
    @Column(name = "cost_type")
    private PremisesCostType costType;

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "premises_id")
//    private Premises premises;

    @OneToMany(mappedBy = "premisesCost", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PremisesCostDetail> premisesCostDetails = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PremisesCostType getCostType() {
        return costType;
    }

    public void setCostType(PremisesCostType costType) {
        this.costType = costType;
    }

    public Set<PremisesCostDetail> getPremisesCostDetails() {
        return premisesCostDetails;
    }

    public void setPremisesCostDetails(Set<PremisesCostDetail> premisesCostDetails) {
        this.premisesCostDetails = premisesCostDetails;
    }

}
