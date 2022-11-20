package pl.wojdylak.propsi.model;

import javax.persistence.*;
import java.awt.print.Book;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "unit_number")
    private String unitNumber;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Property property;

    public Address() {
    }

    public Address(String zipCode, String city, String street, String streetNumber, String unitNumber) {
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.unitNumber = unitNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void addProperty(Property property) {
        this.property = property;
        property.setAddress(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        return id != null && id.equals(((Address) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", unitNumber='" + unitNumber + '\'' +
                '}';
    }
}
