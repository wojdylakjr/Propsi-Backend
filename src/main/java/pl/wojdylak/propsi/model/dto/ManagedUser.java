package pl.wojdylak.propsi.model.dto;

import pl.wojdylak.propsi.model.Authority;
import pl.wojdylak.propsi.model.User;

import java.util.stream.Collectors;

public class ManagedUser extends UserDto {
    //TODO: validation
    private String password;

    public ManagedUser(){}
    public ManagedUser(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.authorities = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet());
        this.password = user.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return super.toString() + "secret password: " + password;
    }
}
