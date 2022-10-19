package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.propsi.model.Property;
import pl.wojdylak.propsi.repository.PropertyRepository;
import pl.wojdylak.propsi.service.PropertyService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Transactional
public class PropertyResource {
    private final PropertyService propertyService;
    private final PropertyRepository propertyRepository;

    public PropertyResource(PropertyService propertyService, PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
    }

    @GetMapping("/owners/{id}/properties")
    public List<Property> getOwnerProperties(@PathVariable Long id ) {
        System.out.println("Owner id: " + id.toString());
//        System.out.println("owner: " + ownerService.getOwner(ownerId).get());
        List<Property> ownerProperties = propertyService.getOwnerProperties(id);
        System.out.println(ownerProperties.toString());
        return ownerProperties;
    }

    @GetMapping("/properties")
//    @ResponseBody
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @PostMapping("/owners/{id}/properties")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProperty(@PathVariable Long id, @RequestBody Property property) {
        System.out.println(property);
        propertyService.addProperty(property,id);
    }
}


