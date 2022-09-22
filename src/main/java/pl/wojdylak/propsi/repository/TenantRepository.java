package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.propsi.model.Authority;
import pl.wojdylak.propsi.model.Tenant;

import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

}
