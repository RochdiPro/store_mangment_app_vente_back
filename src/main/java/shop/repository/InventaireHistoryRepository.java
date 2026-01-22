package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.model.InventaireHistory;

public interface InventaireHistoryRepository extends JpaRepository<InventaireHistory, Long> {
}