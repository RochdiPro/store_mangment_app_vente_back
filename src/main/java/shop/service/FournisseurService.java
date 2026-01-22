package shop.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.model.Fournisseur;
import shop.repository.FournisseurRepository;

import java.util.List;

@Service
@Transactional
public class FournisseurService {

    private final FournisseurRepository repository;

    public FournisseurService(FournisseurRepository repository) {
        this.repository = repository;
    }

    // === getAll() ===
    public List<Fournisseur> getAll() {
        return repository.findAll();
    }

    // === getById() ===
    public Fournisseur getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // === add() ===
    public Fournisseur add(Fournisseur fournisseur) {
        if (fournisseur.getSolde() == null) {
            fournisseur.setSolde(0.0);
        }
        return repository.save(fournisseur);
    }

    // === nameExists() ===
    public boolean nameExists(String nom, Long excludeId) {
        return repository.findAll().stream()
                .anyMatch(f ->
                        f.getNom().equalsIgnoreCase(nom)
                                && (excludeId == null || !f.getId().equals(excludeId))
                );
    }

    // === update() ===
    public Fournisseur update(Long id, Fournisseur fournisseur) {
        fournisseur.setId(id);
        return repository.save(fournisseur);
    }

    // === delete() ===
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // === updateSolde() ===
    public void updateSolde(Long id, Double montant) {
        Fournisseur fournisseur = getById(id);
        if (fournisseur != null) {
            fournisseur.setSolde(fournisseur.getSolde() + montant);
            repository.save(fournisseur);
        }
    }
}
