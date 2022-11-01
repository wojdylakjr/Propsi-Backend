package pl.wojdylak.propsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "premises")
public class Premises implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;


//TODO: to lazy
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "property_id")
    private Property property;


    @JsonIgnore
    @OneToMany(mappedBy = "premises", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rental> rentals = new HashSet<>();


    @OneToMany(mappedBy = "premises", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PremisesCost> premisesCosts = new HashSet<>();

    @OneToMany(mappedBy = "premises", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Meter> meters = new HashSet<>();

    public Premises() {
    }

    public Premises(String name) {
        this.name = name;
    }

    public Premises(String name, Double rentPrice, Property property, Set<Rental> rentals) {
        this.name = name;
        this.property = property;
        this.rentals = rentals;
    }

    public Premises(Long id, String name, Double rentPrice, Property property, Set<Rental> rentals) {
        this.id = id;
        this.name = name;
        this.property = property;
        this.rentals = rentals;
    }

    public Premises(Long id, String name, Property property, Set<Rental> rentals, Set<PremisesCost> premisesCost, Set<Meter> meters) {
        this.id = id;
        this.name = name;
        this.property = property;
        this.rentals = rentals;
        this.premisesCosts = premisesCost;
        this.meters = meters;
    }

    public Premises(String name, Property property, Set<PremisesCost> premisesCost, Set<Meter> meters) {
        this.name = name;
        this.property = property;
        this.premisesCosts = premisesCost;
        this.meters = meters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Set<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(Set<Rental> rentals) {
        this.rentals = rentals;
    }

    public Set<PremisesCost> getPremisesCosts() {
        return premisesCosts;
    }

    public void setPremisesCosts(Set<PremisesCost> premisesCosts) {
        this.premisesCosts = premisesCosts;
    }

    public Set<Meter> getMeters() {
        return meters;
    }

    public void setMeters(Set<Meter> meters) {
        this.meters = meters;
    }

    public void addProperty(Property property) {
        this.property = property;
        property.getPremises().add(this);
    }

    public void addTenant(Tenant tenant) {
        Rental rental = new Rental(tenant, this);
        rentals.add(rental);
        tenant.getRentals().add(rental);
    }

    public void addPremisesCosts(Set<PremisesCost> premisesCosts) {
        this.premisesCosts = premisesCosts;
        premisesCosts.forEach(premisesCost -> premisesCost.setPremises(this));
    }

    public void addPremisesMeters(Set<Meter> meters) {
        this.meters = meters;
        meters.forEach(meter -> meter.setPremises(this));
    }


    public void removeTenant(Tenant tenant) {
//        TODO:implement - https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Premises)) {
            return false;
        }
        return id != null && id.equals(((Premises) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        String propertyS = "";
        if (property == null) {
            propertyS = "null";
        } else {
            propertyS = property.getName();
        }
        return "Premises{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", property=" + propertyS +
                ", rentals=" + rentals +
                ", premisesCosts=" + premisesCosts +
                ", meters=" + meters +
                '}';
    }


}
