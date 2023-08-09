package pl.wojdylak.propsi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.propsi.model.Meter;
import pl.wojdylak.propsi.model.MeterMeasurement;
import pl.wojdylak.propsi.service.MeterService;
import pl.wojdylak.propsi.model.dto.MeterMeasurementRequestDto;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MetersResource {
    private final MeterService meterService;

    public MetersResource(MeterService meterService) {
        this.meterService = meterService;
    }

    @GetMapping("/owners/{ownerId}/premises/{premisesId}/meters")
    public List<Meter> getPremisesCostForOnePremises(@PathVariable Long ownerId, @PathVariable Long premisesId) {
        return this.meterService.getAllMetersForOnePremises(ownerId, premisesId);
    }

    @GetMapping("/tenants/{tenantId}/premises/{premisesId}/meters")
    public List<Meter> getPremisesCostForOneTenantPremises(@PathVariable Long tenantId, @PathVariable Long premisesId) {
        return this.meterService.getAllMetersForOnePremises(tenantId, premisesId);
    }

    @GetMapping("/owners/{ownerId}/premises/{premisesId}/metersMeasurements")
    public List<MeterMeasurement> getAllMetersMeasurementsForOneOwnerPremises(@PathVariable Long ownerId, @PathVariable Long premisesId) {
        return this.meterService.getAllMetersMeasurementsForOnePremises(ownerId, premisesId);
    }

    @GetMapping("/tenants/{tenantId}/premises/{premisesId}/metersMeasurements")
    public List<MeterMeasurement> getAllMetersMeasurementsForOneTenantPremises(@PathVariable Long tenantId, @PathVariable Long premisesId) {
        return this.meterService.getAllMetersMeasurementsForOnePremises(tenantId, premisesId);
    }

    @PostMapping("/meterMeasurements")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMeterMeasurement(@RequestBody MeterMeasurementRequestDto meterMeasurementRequestDto) {
        System.out.println(meterMeasurementRequestDto);
        meterService.addMeterMeasurement(meterMeasurementRequestDto);
    }
}
