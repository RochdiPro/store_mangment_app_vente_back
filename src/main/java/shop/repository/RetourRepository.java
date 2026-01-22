package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.model.Retour;

public interface RetourRepository extends JpaRepository<Retour, Long> {
}