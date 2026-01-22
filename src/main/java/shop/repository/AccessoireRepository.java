package shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import shop.model.Accessoire;


@Repository
public interface AccessoireRepository extends JpaRepository<Accessoire, Long> {

    List<Accessoire> findByQuantiteGreaterThan(Integer quantite);
}