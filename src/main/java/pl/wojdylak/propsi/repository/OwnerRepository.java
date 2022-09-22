package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.propsi.model.Authority;
import pl.wojdylak.propsi.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
