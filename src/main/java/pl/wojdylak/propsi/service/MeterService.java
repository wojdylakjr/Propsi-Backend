package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Meter;
import pl.wojdylak.propsi.model.MeterMeasurement;
import pl.wojdylak.propsi.repository.MeterMeasurementRepository;
import pl.wojdylak.propsi.repository.MeterRepository;
import pl.wojdylak.propsi.service.dto.MeterMeasurementRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeterService {
    private final MeterRepository meterRepository;
    private final MeterMeasurementRepository meterMeasurementRepository;

    public MeterService(MeterRepository meterRepository, MeterMeasurementRepository meterMeasurementRepository) {
        this.meterRepository = meterRepository;
        this.meterMeasurementRepository = meterMeasurementRepository;
    }

    public Optional<Meter> getMeterById(Long id) {
        return this.meterRepository.findById(id);
    }

    public List<Meter> getAllMetersForOnePremises(Long ownerId, Long premisesId) {
        return this.meterRepository.findAll()
                .stream()
                .filter(meter -> meter.getPremises().getId().equals(premisesId))
                .collect(Collectors.toList());
    }

    public List<MeterMeasurement> getAllMetersMeasurementsForOnePremises(Long ownerId, Long premisesId) {
        return this.meterMeasurementRepository.findAll()
                .stream()
                .filter(meterMeasurement -> meterMeasurement.getMeter().getPremises().getId().equals(premisesId))
                .collect(Collectors.toList());
    }

    public void addMeterMeasurement(MeterMeasurementRequestDto meterMeasurementRequestDto) {
        //TODO; refactor
        Optional<Meter> meterById = this.getMeterById(meterMeasurementRequestDto.getMeterId());
        if(meterById.isPresent()){
            MeterMeasurement meterMeasurement = new MeterMeasurement();
            meterMeasurement.setValue(meterMeasurementRequestDto.getValue());
            meterMeasurement.setUnit(meterMeasurementRequestDto.getUnit());
            meterMeasurement.setDate(meterMeasurementRequestDto.getDate());
            meterMeasurement.addMeter(meterById.get());
            this.meterMeasurementRepository.save(meterMeasurement);
        }
    }
}
