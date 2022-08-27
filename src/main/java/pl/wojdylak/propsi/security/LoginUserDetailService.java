package pl.wojdylak.propsi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.wojdylak.propsi.model.User;
import pl.wojdylak.propsi.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class LoginUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public LoginUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());

        //TODO: inline this
        org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        System.out.println(user1);
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        return user1;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
