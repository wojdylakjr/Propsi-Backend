package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.propsi.model.PremisesCost;
import pl.wojdylak.propsi.model.PremisesCostDetail;
import pl.wojdylak.propsi.service.PremisesCostService;
import pl.wojdylak.propsi.model.dto.PremisesCostDetailRequestDto;


import java.util.List;

@RestController
@RequestMapping("/api")
public class PremisesCostsResource {
    private final PremisesCostService premisesCostService;

    public PremisesCostsResource(PremisesCostService premisesCostService) {
        this.premisesCostService = premisesCostService;
    }

    @GetMapping("/owners/{ownerId}/premises/{premisesId}/premisesCosts")
    public List<PremisesCost> getPremisesCostForOnePremises(@PathVariable Long ownerId, @PathVariable Long premisesId) {
        return this.premisesCostService.getAllPremisesCostsForOnePremises(ownerId, premisesId);
    }

    @GetMapping("/owners/{ownerId}/premises/{premisesId}/premisesCostsDetails")
    public List<PremisesCostDetail> getPremisesCostDetailsForOnePremises(@PathVariable Long ownerId, @PathVariable Long premisesId) {
        return this.premisesCostService.getAllPremisesCostsDetailForOnePremises(ownerId, premisesId);
    }

    @PostMapping("/premisesCostDetail")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMeterMeasurement(@RequestBody PremisesCostDetailRequestDto premisesCostDetailRequestDto) {
        System.out.println(premisesCostDetailRequestDto);
        premisesCostService.addPremisesCostDetail(premisesCostDetailRequestDto);
    }
}
