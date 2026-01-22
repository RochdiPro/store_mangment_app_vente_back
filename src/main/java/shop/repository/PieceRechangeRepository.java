package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.model.PieceRechange;

public interface PieceRechangeRepository extends JpaRepository<PieceRechange, Long> {
}