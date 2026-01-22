package shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.DefautStandard;

@Repository
public interface DefautStandardRepository extends JpaRepository<DefautStandard, Long> {
}
