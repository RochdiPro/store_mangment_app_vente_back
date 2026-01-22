package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.Telephone;

import java.util.List;
import java.util.Optional;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
    Optional<Telephone> findByImei1(String imei1);

    Optional<Telephone> findByImei1AndIdNot(String imei1, Long id);

    List<Telephone> findByVenduFalse();

    List<Telephone> findByClientId(Long clientId);

    List<Telephone> findByEtat(Telephone.EtatTelephone etat);

    List<Telephone> findByImei1ContainingIgnoreCaseOrImei2ContainingIgnoreCase(String imei1, String imei2);
}
