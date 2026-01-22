package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.Caisse;

import java.util.Optional;

@Repository
public interface CaisseRepository extends JpaRepository<Caisse, Long> {

    Optional<Caisse> findByIsOpenTrue();
}
