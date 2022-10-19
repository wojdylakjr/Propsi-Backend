package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Owner;
import pl.wojdylak.propsi.model.Property;
import pl.wojdylak.propsi.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
    public Optional<Owner> getOwner(Long ownerId) {
//        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println("Security context: " + name);
        return ownerRepository.findById(ownerId);
    }


    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }
}
