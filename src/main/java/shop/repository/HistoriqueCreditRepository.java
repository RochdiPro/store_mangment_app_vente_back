package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.HistoriqueCredit;

import java.util.List;

@Repository
public interface HistoriqueCreditRepository extends JpaRepository<HistoriqueCredit, Long> {
    List<HistoriqueCredit> findByType(String type);
    List<HistoriqueCredit> findByTypeAndReferenceId(String type, Long referenceId);
}

