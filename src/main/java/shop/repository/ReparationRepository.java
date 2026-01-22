package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.model.Reparation;

import java.util.List;

public interface ReparationRepository extends JpaRepository<Reparation, Long> {

    List<Reparation> findByClient_Id(Long clientId);

    List<Reparation> findByEtat(Reparation.Etat etat);
}