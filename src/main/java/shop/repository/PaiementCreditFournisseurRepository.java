package shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.PaiementCreditFournisseur;

import java.util.List;

@Repository
public interface PaiementCreditFournisseurRepository extends JpaRepository<PaiementCreditFournisseur, Long> {
    List<PaiementCreditFournisseur> findByCreditId(Long creditId);
}
