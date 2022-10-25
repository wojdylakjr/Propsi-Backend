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

    @Enumerated(EnumType.STRING)
    @Column(name = "meter_type")
    private MeterType meterType;

//    @OneToMany(mappedBy = "meter", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<MeterMeasurement> meterMeasurements = new HashSet<>();

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "premises_id")
//    private Premises premises;

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
}