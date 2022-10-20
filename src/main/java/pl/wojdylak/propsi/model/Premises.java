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

    @Column(name = "rent_price")
    private Double rentPrice;

   @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="property_id")
    private Property property;


   @JsonIgnore
   @OneToMany(mappedBy = "premises", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rental> rentals = new HashSet<>();

    public Premises() {
    }

    public Premises(String name) {
        this.name = name;
    }

    public Premises(String name, Double rentPrice, Property property, Set<Rental> rentals) {
        this.name = name;
        this.rentPrice = rentPrice;
        this.property = property;
        this.rentals = rentals;
    }

    public Premises(Long id, String name, Double rentPrice, Property property, Set<Rental> rentals) {
        this.id = id;
        this.name = name;
        this.rentPrice = rentPrice;
        this.property = property;
        this.rentals = rentals;
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

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
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

    public void addProperty(Property property){
        this.property = property;
        property.getPremises().add(this);
    }

    public void addTenant(Tenant tenant){
        Rental rental = new Rental(tenant,this);
        rentals.add(rental);
        tenant.getRentals().add(rental);
    }

    public void removeTenant(Tenant tenant){
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
        if(property ==null){
            propertyS = "null";
        }else {
            propertyS = property.getName();
        }
        return "Premises{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rentPrice=" + rentPrice +
                ", property=" + propertyS +
                ", rentals=" + rentals +
                '}';
    }
}
