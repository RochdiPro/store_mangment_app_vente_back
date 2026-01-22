package shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.TestEntreeSortie;

import java.util.List;

@Repository
public interface TestEntreeSortieRepository extends JpaRepository<TestEntreeSortie, Long> {

    List<TestEntreeSortie> findByTypeOrderByOrdre(TestEntreeSortie.Type type);

}

