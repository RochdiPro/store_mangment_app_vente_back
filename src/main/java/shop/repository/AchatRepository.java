package shop.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.Achat;

import java.util.List;

@Repository
public interface AchatRepository extends JpaRepository<Achat, Long> {
    List<Achat> findByFournisseurId(Long fournisseurId);
}
