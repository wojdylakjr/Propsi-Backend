package pl.wojdylak.propsi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "property")
public class Property implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

//TODO: uncomment

//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    private Address address;

    //    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
//    private Set<PropertyFixedCost> fixedCosts = new HashSet<>();

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private Set<Premises> premises = new HashSet<>();

    //TODO: change fetch type to lazy! ...?
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner owner;

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

    public Set<Premises> getPremises() {
        return premises;
    }

    public void setPremises(Set<Premises> premises) {
        this.premises = premises;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Property)) {
            return false;
        }
        return id != null && id.equals(((Property) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", premises=" + premises +
                ", owner=" + owner +
                '}';
    }
}
