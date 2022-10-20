package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.propsi.model.Rental;


public interface RentalRepository extends JpaRepository<Rental, Long> {
}
