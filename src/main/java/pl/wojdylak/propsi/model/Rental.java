package pl.wojdylak.propsi.model;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "rental")
public class Rental implements Serializable {
    @EmbeddedId
    private RentalId id;

    //TODO: Change to LAZY FetchType !!!
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("tenantId")
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("premisesId")
    private Premises premises;

    @Column(name = "name")
    private String name;

    public Rental() {
    }

    public Rental(Tenant tenant, Premises premises) {
        this.tenant = tenant;
        this.premises = premises;
    }

    public RentalId getId() {
        return id;
    }

    public void setId(RentalId id) {
        this.id = id;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Premises getPremises() {
        return premises;
    }

    public void setPremises(Premises premises) {
        this.premises = premises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rental)) {
            return false;
        }
        return id != null && id.equals(((Rental) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", tenant name=" + tenant.getName() +
                ", premises=" + premises.getName() +
                ", name='" + name + '\'' +
                '}';
    }
}
