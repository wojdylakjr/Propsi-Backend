package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Owner;
import pl.wojdylak.propsi.model.Property;
import pl.wojdylak.propsi.repository.OwnerRepository;

import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final PropertyService propertyService;

    public OwnerService(OwnerRepository ownerRepository, PropertyService propertyService) {
        this.ownerRepository = ownerRepository;
        this.propertyService = propertyService;
    }
    public Optional<Owner> getOwner(Long ownerId) {
//        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println("Security context: " + name);
        return ownerRepository.findById(ownerId);
    }

    public void addProperty(Property property, Long ownerId) {
        this.propertyService.saveProperty(property);
    }
}
