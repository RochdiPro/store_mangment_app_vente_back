package shop.service;

import org.springframework.stereotype.Service;
import shop.model.TestEntreeSortie;
import shop.repository.TestEntreeSortieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TestEntreeSortieService {

    private final TestEntreeSortieRepository repository;

    public TestEntreeSortieService(TestEntreeSortieRepository repository) {
        this.repository = repository;
        initializeDefaultTests();
    }

    // ==========================
    // Méthodes CRUD
    // ==========================
    public List<TestEntreeSortie> getAll() {
        return repository.findAll();
    }

    public List<TestEntreeSortie> getByType(TestEntreeSortie.Type type) {
        return repository.findByTypeOrderByOrdre(type);
    }

    public Optional<TestEntreeSortie> getById(Long id) {
        return repository.findById(id);
    }

    public TestEntreeSortie add(TestEntreeSortie test) {
        return repository.save(test);
    }

    public boolean update(TestEntreeSortie test) {
        if (!repository.existsById(test.getId())) return false;
        repository.save(test);
        return true;
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    // ==========================
    // Logique spécifique
    // ==========================
    public void updateOrder(List<TestEntreeSortie> tests) {
        for (int i = 0; i < tests.size(); i++) {
            TestEntreeSortie test = tests.get(i);
            test.setOrdre(i + 1);
            repository.save(test);
        }
    }

    private void initializeDefaultTests() {
        if (repository.count() > 0) return;

        add(new TestEntreeSortie(null, "Écran tactile", TestEntreeSortie.Type.ENTREE, 1));
        add(new TestEntreeSortie(null, "Boutons physiques", TestEntreeSortie.Type.ENTREE, 2));
        add(new TestEntreeSortie(null, "Capteur d'empreintes", TestEntreeSortie.Type.ENTREE, 3));
        add(new TestEntreeSortie(null, "Caméras", TestEntreeSortie.Type.ENTREE, 4));
        add(new TestEntreeSortie(null, "Haut-parleurs", TestEntreeSortie.Type.ENTREE, 5));
        add(new TestEntreeSortie(null, "Microphone", TestEntreeSortie.Type.ENTREE, 6));
        add(new TestEntreeSortie(null, "Charge de batterie", TestEntreeSortie.Type.ENTREE, 7));
        add(new TestEntreeSortie(null, "État physique", TestEntreeSortie.Type.ENTREE, 8));

        add(new TestEntreeSortie(null, "Test complet effectué", TestEntreeSortie.Type.SORTIE, 1));
        add(new TestEntreeSortie(null, "Réparation validée", TestEntreeSortie.Type.SORTIE, 2));
        add(new TestEntreeSortie(null, "Nettoyage effectué", TestEntreeSortie.Type.SORTIE, 3));
    }
}
