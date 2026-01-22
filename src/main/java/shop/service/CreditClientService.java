package shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.model.CreditClient;
import shop.model.PaiementCreditClient;
import shop.repository.CreditClientRepository;
import shop.repository.PaiementCreditClientRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditClientService {

    private final CreditClientRepository creditRepo;
    private final PaiementCreditClientRepository paiementRepo;
    private final CaisseService caisseService;

    public List<CreditClient> getAll() {
        return creditRepo.findAll();
    }

    public CreditClient getById(Long id) {
        return creditRepo.findById(id).orElse(null);
    }

    public List<CreditClient> getByClient(Long clientId) {
        return creditRepo.findByClientId(clientId);
    }

    public List<CreditClient> getActiveByClient(Long clientId) {
        return creditRepo.findByClientIdAndStatut(clientId, CreditClient.Statut.ACTIF);
    }

    public CreditClient addCredit(CreditClient credit) {
        return creditRepo.save(credit);
    }

    public CreditClient updateCredit(CreditClient credit) {
        return creditRepo.save(credit);
    }

    public void deleteCredit(Long id) {
        creditRepo.deleteById(id);
    }

    @Transactional
    public void addPaiement(Long creditId, double montant, String description) {
        CreditClient credit = getById(creditId);
        if (credit != null) {
            double reste = credit.getReste() - montant;
            credit.setReste(Math.max(reste, 0));
            if (credit.getReste() <= 0) {
                credit.setStatut(CreditClient.Statut.PAYE);
            }
            updateCredit(credit);

            PaiementCreditClient paiement = new PaiementCreditClient();
            paiement.setCreditId(creditId);
            paiement.setCreditType(PaiementCreditClient.CreditType.CLIENT);
            paiement.setDate(new Date());
            paiement.setMontant(montant);
            paiement.setDescription(description);

            paiementRepo.save(paiement);

            // Ajuster la caisse
            caisseService.ajusterSolde(creditId, montant, "Paiement crédit client #" + creditId, "PAIEMENT_CREDIT_CLIENT", creditId, 0);
        }
    }

    public List<PaiementCreditClient> getPaiementsByCredit(Long creditId) {
        return paiementRepo.findByCreditId(creditId);
    }

    public double getTotalCreditByClient(Long clientId) {
        return getActiveByClient(clientId).stream()
                .mapToDouble(CreditClient::getReste)
                .sum();
    }
}
