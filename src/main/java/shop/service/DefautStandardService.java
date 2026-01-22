package shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.model.DefautStandard;
import shop.repository.DefautStandardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefautStandardService {

    private final DefautStandardRepository repository;

    public List<DefautStandard> getAll() {
        return repository.findAll();
    }

    public DefautStandard getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public DefautStandard add(DefautStandard defaut) {
        return repository.save(defaut);
    }

    public DefautStandard update(DefautStandard defaut) {
        return repository.save(defaut);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
