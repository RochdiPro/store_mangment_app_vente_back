package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.model.Stock;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByProduitType(Stock.ProduitType produitType);
}