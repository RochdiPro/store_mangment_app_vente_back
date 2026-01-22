package shop.service;

import org.springframework.stereotype.Service;
import shop.model.Caisse;
import shop.repository.CaisseRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CaisseService {

    private final CaisseRepository caisseRepository;
    private final TraceCaisseService traceCaisseService;
    private final AuthService authService;

    public CaisseService(CaisseRepository caisseRepository, TraceCaisseService traceCaisseService, AuthService authService) {
        this.caisseRepository = caisseRepository;
        this.traceCaisseService = traceCaisseService;
        this.authService = authService;
    }

    public List<Caisse> getAll() {
        return caisseRepository.findAll();
    }

    public Caisse getById(Long id) {
        return caisseRepository.findById(id).orElse(null);
    }

    public Caisse getOpenCaisse() {
        return caisseRepository.findByIsOpenTrue().orElse(null);
    }

    public Caisse add(Caisse caisse) {
        if (caisse.isOpen()) {
            Caisse open = getOpenCaisse();
            if (open != null) {
                throw new RuntimeException("Une caisse est déjà ouverte");
            }
        }
        return caisseRepository.save(caisse);
    }

    public void update(Caisse caisse) {
        caisseRepository.save(caisse);
    }

    public void delete(Long id) {
        caisseRepository.deleteById(id);
    }

    public Caisse openCaisse(double soldeInitial) {
        Caisse open = getOpenCaisse();
        if (open != null) return open;

        var currentUser = authService.getCurrentUser();

        Caisse caisse = new Caisse();
        caisse.setDate(LocalDateTime.now());
        caisse.setSoldeInitial(soldeInitial);
        caisse.setSoldeActuel(soldeInitial);
        caisse.setOpen(true);
        caisse.setUserId(currentUser.getId());
        caisse.setUserName(currentUser.getNom());
        caisse.setBenefice(0);

        caisse = add(caisse);

        traceCaisseService.addTrace(
                caisse.getId(),
                "OUVERTURE",
                soldeInitial,
                0,
                soldeInitial,
                "Ouverture de caisse avec solde initial " + soldeInitial,
                null,
                currentUser.getId(),
                currentUser.getNom(),
                0
        );

        return caisse;
    }

    public void closeCaisse(Long caisseId) {
        Caisse caisse = getById(caisseId);
        if (caisse == null) throw new RuntimeException("Caisse introuvable");
        if (!caisse.isOpen()) throw new RuntimeException("Caisse déjà fermée");

        var currentUser = authService.getCurrentUser();

        traceCaisseService.addTrace(
                caisse.getId(),
                "FERMETURE",
                0,
                caisse.getSoldeActuel(),
                caisse.getSoldeActuel(),
                "Fermeture de caisse avec solde final " + caisse.getSoldeActuel(),
                null,
                currentUser.getId(),
                currentUser.getNom(),
                0
        );

        caisse.setOpen(false);
        update(caisse);
    }

    public void ajusterSolde(Long caisseId, double montant, String description, String action, Long referenceId, Double benefice) {
        Caisse caisse = getById(caisseId);
        if (caisse == null) throw new RuntimeException("Caisse introuvable");

        double nouveauSolde = caisse.getSoldeActuel() + montant;
        double nouveauBenefice = caisse.getBenefice() + (benefice != null ? benefice : 0);
        String actionType = action != null ? action : "AJUSTEMENT";
        String desc = description != null ? description : "Ajustement de " + montant;

        var currentUser = authService.getCurrentUser();

        traceCaisseService.addTrace(
                caisse.getId(),
                actionType,
                montant,
                caisse.getSoldeActuel(),
                nouveauSolde,
                desc,
                referenceId,
                currentUser.getId(),
                currentUser.getNom(),
                benefice
        );

        caisse.setSoldeActuel(nouveauSolde);
        caisse.setBenefice(nouveauBenefice);
        update(caisse);
    }

    public void ensureCaisseOpen() {
        if (getOpenCaisse() == null) openCaisse(0);
    }
}
