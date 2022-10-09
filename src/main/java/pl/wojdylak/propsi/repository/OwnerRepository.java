package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.propsi.model.Authority;
import pl.wojdylak.propsi.model.Owner;
import pl.wojdylak.propsi.model.User;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findById(Long id);
}
