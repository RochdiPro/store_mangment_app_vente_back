package shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.Modele;

import java.util.List;

@Repository
public interface ModeleRepository extends JpaRepository<Modele, Long> {

    List<Modele> findByMarqueId(Long marqueId);
}
