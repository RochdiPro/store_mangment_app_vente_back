package com.example.shop.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.model.Accessoire;
import shop.model.Achat;
import shop.model.AchatItem;
import shop.repository.AchatRepository;
import shop.service.AccessoireService;
import shop.service.CreditFournisseurService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchatService {

    private final AchatRepository achatRepository;
    private final AccessoireService accessoireService;
    private final CreditFournisseurService creditFournisseurService;

    public List<Achat> getAll() {
        return achatRepository.findAll();
    }

    public Achat getById(Long id) {
        return achatRepository.findById(id).orElse(null);
    }

    @Transactional
    public Achat add(Achat achat) {
        Achat savedAchat = achatRepository.save(achat);

        // Augmenter l’inventaire
        if (achat.getItems() != null) {
            for (AchatItem item : achat.getItems()) {
                if ("ACCESSOIRE".equals(item.getType())) {
                    Accessoire accessoire = accessoireService.getById(item.getId());
                    if (accessoire != null) {
                        accessoire.setQuantite(accessoire.getQuantite() + item.getQuantite());
                        accessoireService.update(accessoire);
                    }
                }
            }
        }

        // Ajouter crédit fournisseur si nécessaire
        if (achat.getReste() > 0 && Achat.SrcType.FOURNISSEUR.equals(achat.getSrcType())) {
            creditFournisseurService.addCreditFromAchat(savedAchat);
        }

        return savedAchat;
    }

    public Achat update(Achat achat) {
        return achatRepository.save(achat);
    }

    public void delete(Long id) {
        achatRepository.deleteById(id);
    }

    public List<Achat> getByFournisseur(Long fournisseurId) {
        return achatRepository.findByFournisseurId(fournisseurId);
    }

    @Transactional
    public void addPaiement(Long achatId, double montant, String description) {
        Achat achat = getById(achatId);
        if (achat != null) {
            achat.setAvance(achat.getAvance() + montant);
            achat.setReste(achat.getReste() - montant);
            update(achat);

            if (Achat.SrcType.FOURNISSEUR.equals(achat.getSrcType())) {
                creditFournisseurService.payCreditForAchat(achatId, montant, description);
            }
        }
    }
}
