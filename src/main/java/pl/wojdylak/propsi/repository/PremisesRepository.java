package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.propsi.model.Premises;
import pl.wojdylak.propsi.model.Property;

import java.util.List;

public interface PremisesRepository extends JpaRepository<Premises, Long> {
}
