package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.model.Fournisseur;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
}
