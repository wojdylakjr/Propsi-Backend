package pl.wojdylak.propsi.service.dto;

public class ManagedUser extends UserDto {
    //TODO: validation
    private String password;

    public ManagedUser(){}

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
