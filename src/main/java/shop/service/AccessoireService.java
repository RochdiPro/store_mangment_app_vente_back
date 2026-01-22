package shop.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.model.Accessoire;
import shop.repository.AccessoireRepository;

import java.util.List;

@Service
@Transactional
public class AccessoireService {

    private final AccessoireRepository repository;

    public AccessoireService(AccessoireRepository repository) {
        this.repository = repository;
    }

    public List<Accessoire> getAll() {
        return repository.findAll();
    }

    public Accessoire getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accessoire non trouvé"));
    }

    public Accessoire add(Accessoire accessoire) {
        if (accessoire.getQuantite() == null) {
            accessoire.setQuantite(0);
        }
        return repository.save(accessoire);
    }

    public Accessoire update(Long id, Accessoire accessoire) {
        accessoire.setId(id);
        return repository.save(accessoire);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Accessoire updateQuantite(Long id, Integer quantite) {
        Accessoire accessoire = getById(id);
        accessoire.setQuantite(quantite);
        return repository.save(accessoire);
    }

    public List<Accessoire> getEnStock() {
        return repository.findByQuantiteGreaterThan(0);
    }
}
