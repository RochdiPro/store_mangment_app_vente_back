package shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.model.CreditFournisseur;
import shop.model.PaiementCreditFournisseur;
import shop.repository.CreditFournisseurRepository;
import shop.repository.PaiementCreditFournisseurRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditFournisseurService {

    private final CreditFournisseurRepository creditRepo;
    private final PaiementCreditFournisseurRepository paiementRepo;
    private final CaisseService caisseService; // Service existant pour ajuster la caisse

    public List<CreditFournisseur> getAll() {
        return creditRepo.findAll();
    }

    public CreditFournisseur getById(Long id) {
        return creditRepo.findById(id).orElse(null);
    }

    public List<CreditFournisseur> getByFournisseur(Long fournisseurId) {
        return creditRepo.findByFournisseurId(fournisseurId);
    }

    public List<CreditFournisseur> getActiveByFournisseur(Long fournisseurId) {
        return creditRepo.findByFournisseurIdAndStatut(fournisseurId, CreditFournisseur.Statut.ACTIF);
    }

    public CreditFournisseur addCredit(CreditFournisseur credit) {
        return creditRepo.save(credit);
    }

    public CreditFournisseur updateCredit(CreditFournisseur credit) {
        return creditRepo.save(credit);
    }

    public void deleteCredit(Long id) {
        creditRepo.deleteById(id);
    }

    @Transactional
    public void addPaiement(Long creditId, double montant, String description) {
        CreditFournisseur credit = getById(creditId);
        if (credit != null) {
            double reste = credit.getReste() - montant;
            credit.setReste(Math.max(reste, 0));
            if (credit.getReste() <= 0) {
                credit.setStatut(CreditFournisseur.Statut.PAYE);
            }
            updateCredit(credit);

            PaiementCreditFournisseur paiement = new PaiementCreditFournisseur();
            paiement.setCreditId(creditId);
            paiement.setCreditType(PaiementCreditFournisseur.CreditType.FOURNISSEUR);
            paiement.setDate(new Date());
            paiement.setMontant(montant);
            paiement.setDescription(description);

            paiementRepo.save(paiement);

            // Ajuster la caisse
            caisseService.ajusterSolde(creditId, -montant, "Paiement dette fournisseur #" + creditId, "PAIEMENT_CREDIT_FOURNISSEUR", creditId, 0);
        }
    }

    public List<PaiementCreditFournisseur> getPaiementsByCredit(Long creditId) {
        return paiementRepo.findByCreditId(creditId);
    }

    public double getTotalCreditByFournisseur(Long fournisseurId) {
        return getActiveByFournisseur(fournisseurId).stream()
                .mapToDouble(CreditFournisseur::getReste)
                .sum();
    }
}
