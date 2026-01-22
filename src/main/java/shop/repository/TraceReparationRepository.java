package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.model.TraceReparation;

import java.util.List;

public interface TraceReparationRepository extends JpaRepository<TraceReparation, Long> {

    List<TraceReparation> findByReparation_Id(Long reparationId);
}