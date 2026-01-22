package shop.service;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.model.Marque;
import shop.repository.MarqueRepository;

import java.util.List;

@Service
@Transactional
public class MarqueService {

    private final MarqueRepository repository;

    public MarqueService(MarqueRepository repository) {
        this.repository = repository;
    }

    // === Initialisation des données par défaut ===
    @PostConstruct
    public void initializeData() {
        if (repository.count() == 0) {
            repository.saveAll(List.of(
                    new Marque(null, "Samsung"),
                    new Marque(null, "Apple"),
                    new Marque(null, "Xiaomi"),
                    new Marque(null, "Oppo"),
                    new Marque(null, "Huawei")
            ));
        }
    }

    // === getAll() ===
    public List<Marque> getAll() {
        return repository.findAll();
    }

    // === getById() ===
    public Marque getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // === add() ===
    public Marque add(Marque marque) {
        return repository.save(marque);
    }

    // === update() ===
    public Marque update(Long id, Marque marque) {
        marque.setId(id);
        return repository.save(marque);
    }

    // === delete() ===
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

