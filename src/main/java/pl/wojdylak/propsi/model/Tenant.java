package pl.wojdylak.propsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tenant")
public class Tenant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rental> rentals = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "tenants")
    private Set<User> users = new HashSet<>();

    public Tenant() {
    }

    public Tenant(String name){
        this.name = name;
    }

    public Tenant(Set<User> users) {
        this.users = users;
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


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(Set<Rental> rentals) {
        this.rentals = rentals;
    }

    //from https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tenant)) {
            return false;
        }
        return id != null && id.equals(((Tenant) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        String propertyS = "";
        if(rentals ==null){
            propertyS = "null";
        }else {
            propertyS = rentals.toString();
        }
        return "Tenant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rentals=" + propertyS +
//                ", users=" + users +
                '}';
    }
}