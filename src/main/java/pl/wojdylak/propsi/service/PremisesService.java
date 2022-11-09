package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Premises;
import pl.wojdylak.propsi.model.Property;
import pl.wojdylak.propsi.repository.PremisesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PremisesService {
    private final PremisesRepository premisesRepository;

    public PremisesService(PremisesRepository premisesRepository) {
        this.premisesRepository = premisesRepository;
    }

    public Optional<Premises> getPremisesById(Long id) {
        return premisesRepository.findById(id);
    }

    public List<Premises> getAll(){
        return premisesRepository.findAll();
    }

    //TODO: write query for this
    public List<Premises> getAllForOwner(Long ownerId){
        List<Premises> ownerPremises = premisesRepository.findAll()
                .stream()
                .filter(premises -> premises.getProperty().getOwner().getId().equals(ownerId))
                .collect(Collectors.toList());
        System.out.println(ownerPremises);
        return ownerPremises;
    }


    public Premises getOwnerPremisesId(Long ownerId, Long premisesId){
        Optional<Premises> premises = this.premisesRepository.findById(premisesId);
        if(premises.isPresent()){
            if(premises.get().getProperty().getOwner().getId().equals(ownerId)){
                return premises.get();
            }
        }
        return null;
    }
}
