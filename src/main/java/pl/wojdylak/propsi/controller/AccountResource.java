package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.wojdylak.propsi.model.LoginCredentials;
import pl.wojdylak.propsi.service.UserService;
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
    public void register(@RequestBody UserDto user){
        System.out.println(user);
        userService.register(user);
    }



}
