package pl.wojdylak.propsi.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.*;
import pl.wojdylak.propsi.repository.AuthorityRepository;
import pl.wojdylak.propsi.repository.UserRepository;
import pl.wojdylak.propsi.model.dto.ManagedUser;
import pl.wojdylak.propsi.model.dto.UserDto;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    private final OwnerService ownerService;
    private final TenantService tenantService;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, OwnerService ownerService, TenantService tenantService) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.ownerService = ownerService;
        this.tenantService = tenantService;
    }


    public User register(ManagedUser user) {
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
        createProfilesForUser(user, newUser);
        System.out.println(newUser);
        userRepository.save(newUser);
        return newUser;

    }

    private void createProfilesForUser(UserDto user, User newUser) {
        String ownerName = "Owner " + newUser.getFirstName() + " " + newUser.getLastName();
        String tenantName = "Tenant " + newUser.getFirstName() + " " + newUser.getLastName();

        if (user.getAuthorities().contains("ROLE_TENANT") && user.getAuthorities().contains("ROLE_OWNER")) {
            newUser.addOwner(new Owner(ownerName));
            newUser.addTenant(new Tenant(tenantName));
            return;
        }

        if (user.getAuthorities().contains("ROLE_TENANT")) {
            newUser.addTenant(new Tenant(tenantName));
            return;
        }

        if (user.getAuthorities().contains("ROLE_OWNER")) {
            newUser.addOwner(new Owner(ownerName));
        }

    }


//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }

    public Optional<UserDto> getCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Security context: " + name);
        return userRepository.findUserDtoByEmail(name); //TODO: inline and delete user variable
    }
    public Optional<UserDto> findUserDtoByEmail(String email){
        return userRepository.findUserDtoByEmail(email);
    }

//    public User getUserById(Long userId) {
//        return this.userRepository.findById(userId).get();
//    }
}