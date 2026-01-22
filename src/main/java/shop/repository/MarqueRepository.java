package shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.Marque;

@Repository
public interface MarqueRepository extends JpaRepository<Marque, Long> {
}
