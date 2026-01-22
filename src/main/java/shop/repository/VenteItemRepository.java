package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.model.VenteItem;

public interface VenteItemRepository extends JpaRepository<VenteItem, Long> {
}