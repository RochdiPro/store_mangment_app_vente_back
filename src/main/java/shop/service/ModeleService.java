package shop.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.model.Modele;
import shop.repository.ModeleRepository;

import java.util.List;

@Service
@Transactional
public class ModeleService {

    private final ModeleRepository repository;

    public ModeleService(ModeleRepository repository) {
        this.repository = repository;
    }

    // === Initialisation des données par défaut ===
    @PostConstruct
    public void initializeData() {
        if (repository.count() == 0) {
            repository.saveAll(List.of(
                    new Modele(null, "Galaxy S21", 1L),
                    new Modele(null, "Galaxy A52", 1L),
                    new Modele(null, "iPhone 13", 2L),
                    new Modele(null, "iPhone 12", 2L),
                    new Modele(null, "Redmi Note 10", 3L),
                    new Modele(null, "Poco X3", 3L)
            ));
        }
    }

    // === getAll() ===
    public List<Modele> getAll() {
        return repository.findAll();
    }

    // === getById() ===
    public Modele getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // === getByMarque() ===
    public List<Modele> getByMarque(Long marqueId) {
        return repository.findByMarqueId(marqueId);
    }

    // === add() ===
    public Modele add(Modele modele) {
        return repository.save(modele);
    }

    // === update() ===
    public Modele update(Long id, Modele modele) {
        modele.setId(id);
        return repository.save(modele);
    }

    // === delete() ===
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
