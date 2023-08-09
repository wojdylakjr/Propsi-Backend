package pl.wojdylak.propsi.model.dto;

import pl.wojdylak.propsi.model.Authority;
import pl.wojdylak.propsi.model.Tenant;
import pl.wojdylak.propsi.model.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDto {

    protected Long id;

    protected String firstName;

    protected String lastName;

    protected String email;

    protected Set<String> authorities = new HashSet<>();

    protected Set<OwnerDto> owners = new HashSet<>();

    protected Set<Tenant> tenants = new HashSet<>();

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.authorities = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet());
        this.tenants = user.getTenants();
        this.owners = user.getOwners().stream().map(OwnerDto::new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public Set<OwnerDto> getOwners() {
        return owners;
    }

    public void setOwners(Set<OwnerDto> owners) {
        this.owners = owners;
    }

    public Set<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(Set<Tenant> tenants) {
        this.tenants = tenants;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", authorities=" + authorities +
                ", owners=" + owners +
                ", tenants=" + tenants +
                '}';
    }
}
