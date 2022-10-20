package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Owner;
import pl.wojdylak.propsi.model.Property;
import pl.wojdylak.propsi.repository.PremisesRepository;
import pl.wojdylak.propsi.repository.PropertyRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final PremisesRepository premisesRepository;
    private final OwnerService ownerService;

    public PropertyService(PropertyRepository propertyRepository, PremisesRepository premisesRepository, OwnerService ownerService) {
        this.propertyRepository = propertyRepository;
        this.premisesRepository = premisesRepository;
        this.ownerService = ownerService;
    }

    public List<Property> getOwnerProperties(Long id){
        return propertyRepository.findByOwnerId(id);
    }

    public void addProperty(Property property, Long ownerId) {
        Optional<Owner> owner = ownerService.getOwner(ownerId);
        if(owner.isPresent()){
            if(property.getPremises().size() > 0){
                property.getPremises().stream().forEach(premises -> {
                    premises.addProperty(property);
                });
            }

//            premisesRepository.saveAllAndFlush(property.getPremises());
            System.out.println("Proprty: " + Arrays.toString(property.getPremises().toArray()));
            owner.get().addProperty(property);
        }else{
            //TODO: fix this error
            throw new RuntimeException("Not foound");
        }
        this.propertyRepository.save(property);
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).get();
    }
}
