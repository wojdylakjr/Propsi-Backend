package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.propsi.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
