package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.propsi.model.Premises;
import pl.wojdylak.propsi.model.PremisesCost;

public interface PremisesCostRepository extends JpaRepository<PremisesCost, Long> {

}
