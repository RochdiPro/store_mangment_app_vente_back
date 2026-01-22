package shop.service;

import org.springframework.stereotype.Service;
import shop.model.Telephone;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TelephoneService {

    private final List<Telephone> telephones = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    // === Toutes les téléphones ===
    public List<Telephone> getAll() {
        return telephones;
    }

    // === Telephone par ID ===
    public Optional<Telephone> getById(Long id) {
        return telephones.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    // === Vérifier IMEI unique ===
    public boolean isImeiUnique(String imei1, Long excludeId) {
        return telephones.stream()
                .noneMatch(t -> t.getImei1().equals(imei1) && !t.getId().equals(excludeId));
    }

    // === Ajouter ===
    public Telephone add(Telephone telephone) {
        if (!isImeiUnique(telephone.getImei1(), null)) {
            return null;
        }
        telephone.setId(counter.getAndIncrement());
        telephones.add(telephone);
        return telephone;
    }

    // === Mettre à jour ===
    public boolean update(Telephone telephone) {
        if (!isImeiUnique(telephone.getImei1(), telephone.getId())) {
            return false;
        }
        Optional<Telephone> existing = getById(telephone.getId());
        if (existing.isPresent()) {
            telephones.remove(existing.get());
            telephones.add(telephone);
            return true;
        }
        return false;
    }

    // === Supprimer ===
    public boolean delete(Long id) {
        return getById(id).map(telephones::remove).orElse(false);
    }

    // === Obtenir en stock ===
    public List<Telephone> getEnStock() {
        List<Telephone> result = new ArrayList<>();
        for (Telephone t : telephones) {
            if (!t.isVendu()) result.add(t);
        }
        return result;
    }

    // === Marquer comme vendu ===
    public boolean markAsVendu(Long id, Long clientId, String clientNom, String venteType, String acheteurCin) {
        Optional<Telephone> t = getById(id);
        if (t.isEmpty()) return false;
        Telephone tel = t.get();
        tel.setVendu(true);
        tel.setClientId(clientId);
        tel.setClientNom(clientNom);
        tel.setVenteType(venteType);
        tel.setAcheteurCin(acheteurCin);
        return update(tel);
    }

    // === Rechercher par IMEI ===
    public List<Telephone> searchByImei(String imei) {
        List<Telephone> result = new ArrayList<>();
        String search = imei.toLowerCase();
        for (Telephone t : telephones) {
            if (t.getImei1().toLowerCase().contains(search) ||
                    (t.getImei2() != null && t.getImei2().toLowerCase().contains(search))) {
                result.add(t);
            }
        }
        return result;
    }

    // === Obtenir par client ===
    public List<Telephone> getByClient(Long clientId) {
        List<Telephone> result = new ArrayList<>();
        for (Telephone t : telephones) {
            if (t.getClientId() != null && t.getClientId().equals(clientId)) result.add(t);
        }
        return result;
    }

    // === Obtenir par état ===
    public List<Telephone> getByEtat(String etat) {
        List<Telephone> result = new ArrayList<>();
        for (Telephone t : telephones) {
            if (t.getEtat() != null && t.getEtat().equals(etat)) result.add(t);
        }
        return result;
    }
}
