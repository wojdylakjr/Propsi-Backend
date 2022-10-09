package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.propsi.model.Owner;
import pl.wojdylak.propsi.model.Property;
import pl.wojdylak.propsi.service.OwnerService;

@Controller
@RequestMapping("/api")
public class OwnerResource {
    private final OwnerService ownerService;

    public OwnerResource(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owners/{ownerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Owner> getOwner(@PathVariable Long ownerId ) {
        System.out.println("Owner id: " + ownerId.toString());
        System.out.println("owner: " + ownerService.getOwner(ownerId).get());
        return new ResponseEntity<Owner>(ownerService.getOwner(ownerId).get(), HttpStatus.OK);
    }


    @PostMapping("/owners/{id}/properties")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProperty(@RequestBody Property property) {
        System.out.println(property);
        ownerService.addProperty(property);
    }
}
