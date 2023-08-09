package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.propsi.model.dto.ManagedUser;
import pl.wojdylak.propsi.model.dto.UserDto;
import pl.wojdylak.propsi.service.UserService;

@Controller
@RequestMapping("/api")
public class AccountResource {
    private final UserService userService;

    public AccountResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody ManagedUser user) {
        System.out.println(user);
        userService.register(user);
    }

    @GetMapping("/account")
    public ResponseEntity<UserDto> getAccount() {
        return userService.getCurrentUser()
                .map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
