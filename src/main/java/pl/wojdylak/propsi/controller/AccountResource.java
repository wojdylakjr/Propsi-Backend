package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.propsi.model.LoginCredentials;
import pl.wojdylak.propsi.model.User;
import pl.wojdylak.propsi.service.UserService;
import pl.wojdylak.propsi.service.dto.ManagedUser;
import pl.wojdylak.propsi.service.dto.UserDto;

@Controller
@RequestMapping("/api")
public class AccountResource {
    private final UserService userService;

    public AccountResource (UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody ManagedUser user){
        System.out.println(user);
        userService.register(user);
    }

    //TODO: change to return user without password and add better exception
    @GetMapping("/account")
    public ResponseEntity<UserDto> getAccount(){
        if(userService.getCurrentUser().isPresent()){
            System.out.println("account resource: " + userService.getCurrentUser().get().toString());
            return new ResponseEntity<UserDto>(userService.getCurrentUser().map(UserDto::new).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
