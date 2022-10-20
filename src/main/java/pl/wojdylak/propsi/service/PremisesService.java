package pl.wojdylak.propsi.service;

import org.springframework.stereotype.Service;
import pl.wojdylak.propsi.model.Premises;
import pl.wojdylak.propsi.repository.PremisesRepository;

import java.util.Optional;

@Service
public class PremisesService {
    private final PremisesRepository premisesRepository;

    public PremisesService(PremisesRepository premisesRepository) {
        this.premisesRepository = premisesRepository;
    }

    public Optional<Premises> getPremisesById(Long id) {
        return premisesRepository.findById(id);
    }
}
