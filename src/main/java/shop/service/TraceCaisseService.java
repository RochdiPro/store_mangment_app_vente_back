package shop.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.model.TraceCaisse;
import shop.repository.TraceCaisseRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TraceCaisseService {

    private final TraceCaisseRepository repository;

    public List<TraceCaisse> getAll() {
        return repository.findAll();
    }

    public TraceCaisse getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<TraceCaisse> getByCaisse(Long caisseId) {
        return repository.findByCaisseId(caisseId);
    }

    public TraceCaisse add(TraceCaisse trace) {
        return repository.save(trace);
    }

    public TraceCaisse addTrace(Long caisseId,
                                TraceCaisse.Action action,
                                double montant,
                                double soldeAvant,
                                double soldeApres,
                                String description,
                                Long referenceId,
                                Long userId,
                                String userName,
                                Double benefice) {
        TraceCaisse trace = new TraceCaisse();
        trace.setCaisseId(caisseId);
        trace.setDate(new Date());
        trace.setAction(action);
        trace.setMontant(montant);
        trace.setSoldeAvant(soldeAvant);
        trace.setSoldeApres(soldeApres);
        trace.setDescription(description);
        trace.setReferenceId(referenceId);
        trace.setUserId(userId);
        trace.setUserName(userName);
        trace.setBenefice(benefice);
        return repository.save(trace);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void clear() {
        repository.deleteAll();
    }
}
