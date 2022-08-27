package pl.wojdylak.propsi.service;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Authority;
import pl.wojdylak.propsi.model.User;
import pl.wojdylak.propsi.repository.AuthorityRepository;
import pl.wojdylak.propsi.repository.UserRepository;
import pl.wojdylak.propsi.service.dto.UserDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService  {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;

    }


    public User register(UserDto user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Authority> authoritySet = new HashSet<>();
        for (String authorityName : user.getAuthorities()) {
            Authority authority = new Authority(authorityName);
            authoritySet.add(authority);
            authorityRepository.save(authority);
        }
        newUser.setAuthorities(authoritySet);
        System.out.println(newUser);
        userRepository.save(newUser);
        return newUser;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
