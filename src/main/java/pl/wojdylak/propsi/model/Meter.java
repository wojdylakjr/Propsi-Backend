package pl.wojdylak.propsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import pl.wojdylak.propsi.model.enumaration.MeterType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meter")
public class Meter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

//    @Enumerated(EnumType.STRING)
    @Column(name = "meter_type")
    private String meterType;

    @JsonIgnore
    @OneToMany(mappedBy = "meter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MeterMeasurement> meterMeasurements = new HashSet<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "premises_id")
    private Premises premises;

    public Meter() {
    }

    public Meter(String meterType, Set<MeterMeasurement> meterMeasurements, Premises premises) {
        this.meterType = meterType;
        this.meterMeasurements = meterMeasurements;
        this.premises = premises;
    }

    public Meter(Long id, String meterType, Set<MeterMeasurement> meterMeasurements, Premises premises) {
        this.id = id;
        this.meterType = meterType;
        this.meterMeasurements = meterMeasurements;
        this.premises = premises;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public Set<MeterMeasurement> getMeterMeasurements() {
        return meterMeasurements;
    }

    public void setMeterMeasurements(Set<MeterMeasurement> meterMeasurements) {
        this.meterMeasurements = meterMeasurements;
    }

    public Premises getPremises() {
        return premises;
    }

    public void setPremises(Premises premises) {
        this.premises = premises;
    }

//    public void addPremises(Premises premises){
//        this.premises = premises;
//        premises.getMeters().add(this);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Meter)) {
            return false;
        }
        return id != null && id.equals(((Meter) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Meter{" +
                "id=" + id +
                ", meterType=" + meterType +
                ", meterMeasurements=" + meterMeasurements +
                '}';
    }
}