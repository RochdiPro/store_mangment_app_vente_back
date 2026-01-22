package shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.model.HistoriqueCredit;
import shop.repository.HistoriqueCreditRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriqueCreditService {

    private final HistoriqueCreditRepository repository;

    public List<HistoriqueCredit> getAll() {
        return repository.findAll();
    }

    public HistoriqueCredit getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public HistoriqueCredit add(HistoriqueCredit entry) {
        return repository.save(entry);
    }

    public HistoriqueCredit update(HistoriqueCredit entry) {
        return repository.save(entry);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<HistoriqueCredit> getByType(String type) {
        return repository.findByType(type);
    }

    public void addIfNotExists(HistoriqueCredit entry) {
        List<HistoriqueCredit> existing = repository.findByTypeAndReferenceId(entry.getType(), entry.getReferenceId());
        if (existing.isEmpty()) {
            repository.save(entry);
        }
    }

    public void clearAll() {
        repository.deleteAll();
    }
}
