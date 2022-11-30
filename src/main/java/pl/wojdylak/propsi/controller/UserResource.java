package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.propsi.model.Owner;
import pl.wojdylak.propsi.model.User;
import pl.wojdylak.propsi.security.AuthoritiesConstants;
import pl.wojdylak.propsi.service.UserService;
import pl.wojdylak.propsi.service.dto.UserDto;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    public UserResource (UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.OWNER + "\")")
    @GetMapping("/testOwner")
    public ResponseEntity<String> test(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.TENANT + "\")")
    @GetMapping("/testTenant")
    public ResponseEntity<String> testUser(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

}
