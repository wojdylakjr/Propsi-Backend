package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wojdylak.propsi.model.User;
import pl.wojdylak.propsi.repository.UserRepository;
import pl.wojdylak.propsi.service.UserService;
import pl.wojdylak.propsi.model.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;
    //private final UserRepository userRepository; //TODO:delete

    public UserResource(UserService userService) {
        this.userService = userService;
        //this.userRepository = userRepository;
    }

//    @GetMapping("admin/users")
//    public ResponseEntity<List<UserDto>> getAllUsers() {
//        List<UserDto> users = userService.getAllUsers().stream().map(UserDto::new).collect(Collectors.toList());
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//TODO: delete
    @GetMapping("admin/userdto")
    public ResponseEntity<UserDto> getUserDto() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Security context: " + name);
        System.out.println("DEBUG getUserDto START");
        Optional<UserDto> byEmail = userService.findUserDtoByEmail(name); //TODO: inline and delete user variable
        System.out.println("bbbbbbbb");
        UserDto user = byEmail.get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @GetMapping("admin/users/{userId}")
//    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
//        User user = userService.getUserById(userId);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    //    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.OWNER + "\")")
    @GetMapping("/testOwner")
    public ResponseEntity<String> test() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

    //    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.TENANT + "\")")
    @GetMapping("/testTenant")
    public ResponseEntity<String> testUser() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

}
