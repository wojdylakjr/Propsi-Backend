package pl.wojdylak.propsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "owner")
public class Owner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "payU_client_id")
    private String payUClientId;

    @Column(name = "payU_client_secret")
    private String payUClientSecret;

    @Column(name = "payU_access_token")
    private String payUAccessToken;

    @Column(name = "payU_access_token_expiration")
    private Instant payUAccessTokenExpiration;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Property> properties = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "owners")
    private Set<User> users = new HashSet<>();

    public Owner() {
    }

    public Owner(String name) {
        this.name = name;
    }

    public Owner(Set<User> users) {
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

    public String getPayUClientId() {
        return payUClientId;
    }

    public void setPayUClientId(String payUClientId) {
        this.payUClientId = payUClientId;
    }

    public String getPayUClientSecret() {
        return payUClientSecret;
    }

    public void setPayUClientSecret(String payUClientSecret) {
        this.payUClientSecret = payUClientSecret;
    }

    public String getPayUAccessToken() {
        return payUAccessToken;
    }

    public void setPayUAccessToken(String payUAccessToken) {
        this.payUAccessToken = payUAccessToken;
    }

    public Instant getPayUAccessTokenExpiration() {
        return payUAccessTokenExpiration;
    }

    public void setPayUAccessTokenExpiration(Instant payUAccessTokenExpiration) {
        this.payUAccessTokenExpiration = payUAccessTokenExpiration;
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public void setProperties(Set<Property> properties) {
        this.properties = properties;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addProperty(Property property) {
        this.properties.add(property);
        property.setOwner(this);
    }

    public void removeProperty(Property property) {
        this.properties.remove(property);
        property.setOwner(null);
    }

    //from https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Owner)) {
            return false;
        }
        return id != null && id.equals(((Owner) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", properties=" + properties +
                '}';
    }
}