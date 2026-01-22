package shop.service;



import org.springframework.stereotype.Service;
import shop.model.PieceRechange;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PieceRechangeService {

    private final List<PieceRechange> pieces = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1); // pour simuler l’ID auto-increment

    // === Toutes les pièces ===
    public List<PieceRechange> getAll() {
        return pieces;
    }

    // === Obtenir une pièce par ID ===
    public Optional<PieceRechange> getById(Long id) {
        return pieces.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    // === Ajouter une pièce ===
    public PieceRechange add(PieceRechange piece) {
        piece.setId(counter.getAndIncrement());
        pieces.add(piece);
        return piece;
    }

    // === Mettre à jour une pièce ===
    public boolean update(PieceRechange piece) {
        Optional<PieceRechange> existing = getById(piece.getId());
        if (existing.isPresent()) {
            pieces.remove(existing.get());
            pieces.add(piece);
            return true;
        }
        return false;
    }

    // === Supprimer une pièce ===
    public boolean delete(Long id) {
        return getById(id).map(p -> pieces.remove(p)).orElse(false);
    }

    // === Décrémenter le stock ===
    public boolean decrementStock(Long id, int quantite) {
        Optional<PieceRechange> piece = getById(id);
        if (piece.isEmpty() || piece.get().getQuantite() < quantite) return false;
        piece.get().setQuantite(piece.get().getQuantite() - quantite);
        return true;
    }

    // === Incrémenter le stock ===
    public boolean incrementStock(Long id, int quantite) {
        Optional<PieceRechange> piece = getById(id);
        if (piece.isEmpty()) return false;
        piece.get().setQuantite(piece.get().getQuantite() + quantite);
        return true;
    }

    // === Pièces en rupture de stock ===
    public List<PieceRechange> getPiecesEnRupture() {
        List<PieceRechange> result = new ArrayList<>();
        for (PieceRechange p : pieces) {
            if (p.getQuantite() <= 0) result.add(p);
        }
        return result;
    }

    // === Pièces à faible stock ===
    public List<PieceRechange> getPiecesFaibleStock(int seuil) {
        List<PieceRechange> result = new ArrayList<>();
        for (PieceRechange p : pieces) {
            if (p.getQuantite() > 0 && p.getQuantite() <= seuil) result.add(p);
        }
        return result;
    }

    // === Mettre à jour la quantité directement ===
    public boolean updateQuantite(Long id, int nouvelleQuantite) {
        Optional<PieceRechange> piece = getById(id);
        if (piece.isEmpty()) return false;
        piece.get().setQuantite(nouvelleQuantite);
        return true;
    }
}
