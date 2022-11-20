package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Owner;
import pl.wojdylak.propsi.repository.OwnerRepository;
import pl.wojdylak.propsi.service.dto.OwnerPayUCredentialsDto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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

    public Owner addPayUCredentials(OwnerPayUCredentialsDto ownerPayUCredentialsDto, Long id) {
        Optional<Owner> owner = this.ownerRepository.findById(id);
        if(owner.isPresent()){
            owner.get().setPayUClientId(ownerPayUCredentialsDto.getPayUClientId());
            owner.get().setPayUClientSecret(ownerPayUCredentialsDto.getPayUClientSecret());
            this.ownerRepository.save(owner.get());
            return owner.get();
        }
       return null;
    }

    public Owner updatePayUToken(String token, Long tokenExpirationInSeconds, Long ownerId) {
        Optional<Owner> owner = this.ownerRepository.findById(ownerId);
        Instant tokenExpiration = Instant.now().plus(tokenExpirationInSeconds, ChronoUnit.SECONDS);
        if(owner.isPresent()){
            owner.get().setPayUAccessToken(token);
            owner.get().setPayUAccessTokenExpiration(tokenExpiration);
            this.ownerRepository.save(owner.get());
            return owner.get();
        }
        return null;
    }
}
