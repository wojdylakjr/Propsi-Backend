package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Owner;
import pl.wojdylak.propsi.model.User;
import pl.wojdylak.propsi.repository.OwnerRepository;

import java.util.HashSet;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner createOwnerWithCurrentUsers(HashSet<User> currentUsers) {
        String objectName = "OWNER";
        Owner owner = new Owner(currentUsers);
        owner.setName(objectName + " " + owner.getId());
        ownerRepository.save(owner);
        return owner;
    }
}
