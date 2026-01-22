package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.model.PieceUtilisee;

public interface PieceUtiliseeRepository extends JpaRepository<PieceUtilisee, Long> {
}