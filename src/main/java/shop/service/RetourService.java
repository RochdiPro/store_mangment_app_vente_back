package shop.service;


import org.springframework.stereotype.Service;
import shop.model.Retour;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RetourService {

    private final List<Retour> retours = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    // === Toutes les retours ===
    public List<Retour> getAll() {
        return retours;
    }

    // === Retour par ID ===
    public Optional<Retour> getById(Long id) {
        return retours.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    // === Ajouter un retour ===
    public Retour add(Retour retour) {
        retour.setId(counter.getAndIncrement());
        retours.add(retour);
        return retour;
    }

    // === Supprimer un retour ===
    public boolean delete(Long id) {
        return getById(id).map(retours::remove).orElse(false);
    }
}

