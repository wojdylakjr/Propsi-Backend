package pl.wojdylak.propsi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Authority;
import pl.wojdylak.propsi.model.Owner;
import pl.wojdylak.propsi.model.Tenant;
import pl.wojdylak.propsi.model.User;
import pl.wojdylak.propsi.repository.AuthorityRepository;
import pl.wojdylak.propsi.repository.OwnerRepository;
import pl.wojdylak.propsi.repository.TenantRepository;
import pl.wojdylak.propsi.repository.UserRepository;
import pl.wojdylak.propsi.service.dto.UserDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final OwnerRepository ownerRepository;
    private final TenantRepository tenantRepository;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, OwnerRepository ownerRepository, TenantRepository tenantRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.ownerRepository = ownerRepository;
        this.tenantRepository = tenantRepository;
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
        setRoleForUser(user, newUser);
        return newUser;

    }

    private void setRoleForUser(UserDto user, User newUser) {
        HashSet<User> currentUserHashSet = new HashSet<>();
        currentUserHashSet.add(newUser);

        if (user.getAuthorities().contains("ROLE_TENANT") && user.getAuthorities().contains("ROLE_OWNER")) {
            insertUserIntoTenant(currentUserHashSet);
            insertUserIntoOwner(currentUserHashSet);
        }

        if (user.getAuthorities().contains("ROLE_TENANT")) {
            insertUserIntoTenant(currentUserHashSet);
        }

        if (user.getAuthorities().contains("ROLE_OWNER")) {
            insertUserIntoOwner(currentUserHashSet);
        }

    }

    private void insertUserIntoOwner(HashSet<User> currentUserHashSet) {
        Owner owner = new Owner(currentUserHashSet);
        ownerRepository.save(owner);
    }

    private void insertUserIntoTenant(HashSet<User> currentUserHashSet) {
        Tenant tenant = new Tenant(currentUserHashSet);
        tenantRepository.save(tenant);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
