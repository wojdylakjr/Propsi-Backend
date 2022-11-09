package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.propsi.model.Meter;
import pl.wojdylak.propsi.model.MeterMeasurement;

public interface MeterMeasurementRepository extends JpaRepository<MeterMeasurement, Long> {
}
