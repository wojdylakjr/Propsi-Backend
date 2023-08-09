package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.wojdylak.propsi.model.User;
import pl.wojdylak.propsi.model.dto.ManagedUser;
import pl.wojdylak.propsi.model.dto.UserDto;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //    Optional<User> findByEmail(String username);
    @Query("SELECT u FROM User u " +
            "JOIN FETCH u.authorities a " +
            "JOIN FETCH u.owners o " +
            "JOIN FETCH u.tenants t " +
            "WHERE u.email = :email")
    Optional<UserDto> findUserDtoByEmail(String email);

    @Query("SELECT u FROM User u " +
            "JOIN FETCH u.authorities a " +
            "WHERE u.email = :email")
    Optional<ManagedUser> findUserDtoWithPassword(String email);
}

