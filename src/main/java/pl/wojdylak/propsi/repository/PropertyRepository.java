package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.propsi.model.Property;
import pl.wojdylak.propsi.model.User;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByOwnerId(Long ownerId);
}
