package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.propsi.model.Meter;
import pl.wojdylak.propsi.model.PremisesCost;

public interface MeterRepository extends JpaRepository<Meter, Long> {

}
