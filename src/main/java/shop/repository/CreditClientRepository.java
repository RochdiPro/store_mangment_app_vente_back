package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.CreditClient;

import java.util.List;

@Repository
public interface CreditClientRepository extends JpaRepository<CreditClient, Long> {
    List<CreditClient> findByClientId(Long clientId);
    List<CreditClient> findByClientIdAndStatut(Long clientId, CreditClient.Statut statut);
}
