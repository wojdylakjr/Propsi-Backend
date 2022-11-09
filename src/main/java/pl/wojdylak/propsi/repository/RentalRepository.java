package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.propsi.model.Rental;
import pl.wojdylak.propsi.model.RentalId;

import java.util.Optional;


public interface RentalRepository extends JpaRepository<Rental, RentalId> {
   Optional<Rental> findByIdTenantIdAndIdPremisesId(Long tenantId, Long premisesId);
}
