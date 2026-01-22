package shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.TraceCaisse;

import java.util.List;

@Repository
public interface TraceCaisseRepository extends JpaRepository<TraceCaisse, Long> {
    List<TraceCaisse> findByCaisseId(Long caisseId);
}
