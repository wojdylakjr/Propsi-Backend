package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Meter;
import pl.wojdylak.propsi.model.MeterMeasurement;
import pl.wojdylak.propsi.model.PremisesCost;
import pl.wojdylak.propsi.model.PremisesCostDetail;
import pl.wojdylak.propsi.repository.PremisesCostDetailRepository;
import pl.wojdylak.propsi.repository.PremisesCostRepository;
import pl.wojdylak.propsi.service.dto.MeterMeasurementRequestDto;
import pl.wojdylak.propsi.service.dto.PremisesCostDetailRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PremisesCostService {
    private final PremisesCostRepository premisesCostRepository;
    private final PremisesCostDetailRepository premisesCostDetailRepository;

    public PremisesCostService(PremisesCostRepository premisesCostRepository, PremisesCostDetailRepository premisesCostDetailRepository) {
        this.premisesCostRepository = premisesCostRepository;
        this.premisesCostDetailRepository = premisesCostDetailRepository;
    }

    public Optional<PremisesCost> getPremisesCostById(Long id) {
       return this.premisesCostRepository.findById(id);
    }

    public PremisesCost getPremisesCostById(Long ownerId, Long premisesId, Long premisesCostId) {
        Optional<PremisesCost> premisesCostById = this.premisesCostRepository.findById(premisesCostId);
        if (premisesCostById.isPresent()) {
            if (premisesCostById.get().getPremises().getId().equals(premisesId) && premisesCostById.get().getPremises().getProperty().getOwner().getId().equals(ownerId)) {
                return premisesCostById.get();
            }
        }
        return null;
    }

    //TODO: write query for this
    public List<PremisesCost> getAllPremisesCostsForOnePremises(Long ownerId, Long premisesId) {
        return this.premisesCostRepository.findAll()
                .stream()
                .filter(premisesCost -> premisesCost.getPremises().getId().equals(premisesId) && premisesCost.getPremises().getProperty().getOwner().getId().equals(ownerId))
                .collect(Collectors.toList());
    }

    public List<PremisesCostDetail> getAllPremisesCostsDetailForOnePremises(Long ownerId, Long premisesId) {
        return this.premisesCostDetailRepository.findAll()
                .stream()
                .filter(premisesCostDetail -> premisesCostDetail.getPremisesCost().getPremises().getId().equals(premisesId))
                .collect(Collectors.toList());

    }

    public void addPremisesCostDetail(PremisesCostDetailRequestDto costDetailRequestDto) {
        Optional<PremisesCost> premisesCostById = this.getPremisesCostById(costDetailRequestDto.getPremisesCostId());
        if (premisesCostById.isPresent()) {
            PremisesCostDetail premisesCostDetail = new PremisesCostDetail();
            premisesCostDetail.setCostValue(costDetailRequestDto.getCostValue());
            premisesCostDetail.setUnit(costDetailRequestDto.getUnit());
            premisesCostDetail.setDate(costDetailRequestDto.getDate());
            premisesCostDetail.addPremisesCost(premisesCostById.get());
            this.premisesCostDetailRepository.save(premisesCostDetail);
        }
    }
}
