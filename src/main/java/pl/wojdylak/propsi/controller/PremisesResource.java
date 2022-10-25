package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wojdylak.propsi.model.Premises;
import pl.wojdylak.propsi.model.Property;
import pl.wojdylak.propsi.service.PremisesService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PremisesResource {
    private final PremisesService premisesService;

    public PremisesResource(PremisesService premisesService) {
        this.premisesService = premisesService;
    }

    @GetMapping("/premises")
    public List<Premises> getAllProperties() {
        return this.premisesService.getAll();
    }

    @GetMapping("/owners/{ownerId}/premises")
    public List<Premises> getAllPremisesForOwner(@PathVariable Long ownerId) {
        return this.premisesService.getAllForOwner(ownerId);
    }
}
