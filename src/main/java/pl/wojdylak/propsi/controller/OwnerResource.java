package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.propsi.model.Owner;
import pl.wojdylak.propsi.model.User;
import pl.wojdylak.propsi.service.OwnerService;
import pl.wojdylak.propsi.service.dto.OwnerPayUCredentialsDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OwnerResource {
    private final OwnerService ownerService;

    public OwnerResource(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owners/{ownerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Owner> getOwner(@PathVariable Long ownerId) {
        System.out.println("Owner id: " + ownerId.toString());
        System.out.println("owner: " + ownerService.getOwner(ownerId).get());
        return new ResponseEntity<Owner>(ownerService.getOwner(ownerId).get(), HttpStatus.OK);
    }
    @GetMapping("/owners")
    @ResponseStatus(HttpStatus.OK)
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @PatchMapping("/owners/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Owner> addPayUCredentials(@RequestBody OwnerPayUCredentialsDto ownerPayUCredentialsDto, @PathVariable Long id){
        Owner owner = this.ownerService.addPayUCredentials(ownerPayUCredentialsDto, id);
        if(owner==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @GetMapping("/owners/{ownerId}/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsersForOwner(@PathVariable Long ownerId) {
        Owner owner = ownerService.getOwner(ownerId).get();
        return new ArrayList<>(owner.getUsers());
    }
}
