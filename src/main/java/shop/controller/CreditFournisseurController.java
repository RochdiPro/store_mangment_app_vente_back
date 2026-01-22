package shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.model.CreditFournisseur;
import shop.model.PaiementCreditFournisseur;
import shop.service.CreditFournisseurService;

import java.util.List;

@RestController
@RequestMapping("/api/credits-fournisseurs")
@RequiredArgsConstructor
public class CreditFournisseurController {

    private final CreditFournisseurService service;

    @GetMapping
    public List<CreditFournisseur> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CreditFournisseur getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/fournisseur/{fournisseurId}")
    public List<CreditFournisseur> getByFournisseur(@PathVariable Long fournisseurId) {
        return service.getByFournisseur(fournisseurId);
    }

    @GetMapping("/fournisseur/{fournisseurId}/actif")
    public List<CreditFournisseur> getActiveByFournisseur(@PathVariable Long fournisseurId) {
        return service.getActiveByFournisseur(fournisseurId);
    }

    @PostMapping
    public CreditFournisseur add(@RequestBody CreditFournisseur credit) {
        return service.addCredit(credit);
    }

    @PutMapping("/{id}")
    public CreditFournisseur update(@PathVariable Long id, @RequestBody CreditFournisseur credit) {
        credit.setId(id);
        return service.updateCredit(credit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteCredit(id);
    }

    @PostMapping("/{id}/paiement")
    public void addPaiement(@PathVariable Long id,
                            @RequestParam double montant,
                            @RequestParam String description) {
        service.addPaiement(id, montant, description);
    }

    @GetMapping("/{id}/paiements")
    public List<PaiementCreditFournisseur> getPaiementsByCredit(@PathVariable Long id) {
        return service.getPaiementsByCredit(id);
    }
}
