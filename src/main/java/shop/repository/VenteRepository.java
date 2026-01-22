package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.model.Vente;

import java.util.List;

public interface VenteRepository extends JpaRepository<Vente, Long> {

    List<Vente> findByClient_Id(Long clientId);
}