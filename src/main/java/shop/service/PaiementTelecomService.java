package shop.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.model.PaiementTelecom;
import shop.repository.PaiementTelecomRepository;

import java.util.List;

@Service
@Transactional
public class PaiementTelecomService {

    private final PaiementTelecomRepository repository;

    public PaiementTelecomService(PaiementTelecomRepository repository) {
        this.repository = repository;
    }

    // === getAll() ===
    public List<PaiementTelecom> getAll() {
        return repository.findAll();
    }

    // === getById() ===
    public PaiementTelecom getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // === add() ===
    public PaiementTelecom add(PaiementTelecom paiement) {
        return repository.save(paiement);
    }

    // === update() ===
    public PaiementTelecom update(Long id, PaiementTelecom paiement) {
        paiement.setId(id);
        return repository.save(paiement);
    }

    // === delete() ===
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // === getByClient() ===
    public List<PaiementTelecom> getByClient(Long clientId) {
        return repository.findByClientId(clientId);
    }
}

