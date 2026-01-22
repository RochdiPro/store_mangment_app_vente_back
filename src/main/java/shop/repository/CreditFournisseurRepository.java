package shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.CreditFournisseur;

import java.util.List;

@Repository
public interface CreditFournisseurRepository extends JpaRepository<CreditFournisseur, Long> {
    List<CreditFournisseur> findByFournisseurId(Long fournisseurId);
    List<CreditFournisseur> findByFournisseurIdAndStatut(Long fournisseurId, CreditFournisseur.Statut statut);
}
