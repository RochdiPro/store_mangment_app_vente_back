package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.model.TypeAccessoire;

public interface TypeAccessoireRepository extends JpaRepository<TypeAccessoire, Long> {
}
