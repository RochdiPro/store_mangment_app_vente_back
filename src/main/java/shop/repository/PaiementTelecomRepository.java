package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.PaiementTelecom;

import java.util.List;

@Repository
public interface PaiementTelecomRepository extends JpaRepository<PaiementTelecom, Long> {

    List<PaiementTelecom> findByClientId(Long clientId);
}
