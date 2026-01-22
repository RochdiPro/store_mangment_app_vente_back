package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementCreditClientRepository extends JpaRepository<PaiementCreditClient, Long> {
    List<PaiementCreditClient> findByCreditId(Long creditId);
}
